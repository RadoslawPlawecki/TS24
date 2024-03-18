package library.service;

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
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("The user doesn't exist in the database!");
        }
        userRepository.deleteById(id);
    }
}
