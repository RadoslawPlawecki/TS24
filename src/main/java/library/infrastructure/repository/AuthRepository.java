package library.infrastructure.repository;

import library.infrastructure.entity.AuthEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends CrudRepository<AuthEntity, Integer> {
    Optional<AuthEntity> findByUsername(String username);
    Optional<AuthEntity> findByUserId(int userId);
    void deleteByUserId(int userId);
}
