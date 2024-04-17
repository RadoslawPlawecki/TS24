package library.service;

import library.common.UserRole;
import library.controller.DTO.UserDTO.GetUserFullDTO;
import library.exception.UserNotFound;
import library.infrastructure.entity.AuthEntity;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.AuthRepository;
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

    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public List<GetUserFullDTO> getAll() {
        ArrayList<UserEntity> users = (ArrayList<UserEntity>) userRepository.findAll();
        ArrayList<GetUserFullDTO> getUserDTO = new ArrayList<>();
        for (UserEntity user : users) {
            AuthEntity auth = authRepository.findByUserId(user.getId()).orElseThrow(() -> UserNotFound.create(user.getId()));
            getUserDTO.add(mapUser(user, auth));
        }
        return getUserDTO;
    }

    public GetUserFullDTO getOne(int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> UserNotFound.create(id));
        AuthEntity authEntity = authRepository.findByUserId(id).orElseThrow(() -> UserNotFound.create(id));
        return mapUser(userEntity, authEntity);
    }

    @Transactional
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFound.create(id);
        }
        authRepository.deleteByUserId(id);
        userRepository.deleteById(id);
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
