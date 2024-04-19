package library.infrastructure.repository;

import library.infrastructure.entity.AuthEntity;
import library.infrastructure.entity.RentalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {
    List<RentalEntity> findByUserId(int userId);
}
