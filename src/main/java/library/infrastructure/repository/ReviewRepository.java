package library.infrastructure.repository;

import library.infrastructure.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByBookId(int bookId);
    List<ReviewEntity> findByUserId(int userId);
    void deleteByUserId(int userId);
    void deleteByBookId(int bookId);
}
