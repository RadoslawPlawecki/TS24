package library.service;

import library.common.UserRole;
import library.controller.DTO.UserDTO.GetUserFullDTO;
import library.exception.PendingReturn;
import library.exception.UserNotFoundForId;
import library.exception.UserNotFoundForUsername;
import library.infrastructure.entity.AuthEntity;
import library.infrastructure.entity.RentalEntity;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.AuthRepository;
import library.infrastructure.repository.RentalRepository;
import library.infrastructure.repository.ReviewRepository;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final RentalRepository rentalRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository, RentalRepository rentalRepository,
                       ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<GetUserFullDTO> getAll() {
        ArrayList<UserEntity> users = (ArrayList<UserEntity>) userRepository.findAll();
        ArrayList<GetUserFullDTO> getUserDTO = new ArrayList<>();
        for (UserEntity user : users) {
            AuthEntity auth = authRepository.findByUserId(user.getId()).orElseThrow(() -> UserNotFoundForId.create(user.getId()));
            getUserDTO.add(mapUser(user, auth));
        }
        return getUserDTO;
    }

    public GetUserFullDTO getById(int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> UserNotFoundForId.create(id));
        AuthEntity authEntity = authRepository.findByUserId(id).orElseThrow(() -> UserNotFoundForId.create(id));
        return mapUser(userEntity, authEntity);
    }

    public GetUserFullDTO getByUsername(String username) {
        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundForUsername.create(username));
        UserEntity userEntity = userRepository.findById(authEntity.getId()).orElseThrow(() -> UserNotFoundForId.create(authEntity.getId()));
        return mapUser(userEntity, authEntity);
    }

    @Transactional
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundForId.create(id);
        }
        boolean noPendingReturns = false;
        if (!rentalRepository.findByUserId(id).isEmpty()) {
            List<RentalEntity> rentals = rentalRepository.findByUserId(id);
            for (RentalEntity rental : rentals) {
                if (rental.getReturnDate() != null) {
                    noPendingReturns = true;
                } else {
                    throw PendingReturn.create(rental.getBook().getTitle());
                }
            }
        } else {
            authRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        }
        if (noPendingReturns) {
            rentalRepository.deleteByUserId(id);
            reviewRepository.deleteByUserId(id);
            authRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        }
    }

    private GetUserFullDTO mapUser(UserEntity user, AuthEntity auth) {
        String role;
        if (auth.getRole().equals(UserRole.ROLE_ADMIN)) {
            role = "ADMIN";
        } else {
            role = "READER";
        }
        return new GetUserFullDTO(user.getId(), user.getName(), auth.getUsername(), user.getEmail(), role);
    }
}
