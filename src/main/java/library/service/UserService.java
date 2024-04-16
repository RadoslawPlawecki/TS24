package library.service;

import library.exception.UserNotFound;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getOne(int id) {
        return userRepository.findById(id).orElseThrow(() -> UserNotFound.create(id));
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFound.create(id);
        }
        userRepository.deleteById(id);
    }
}
