package library.service;

import library.infrastructure.entity.ReviewEntity;
import library.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Iterable<ReviewEntity> getAll() {
        return reviewRepository.findAll();
    }

    public ReviewEntity getOne(int id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found!"));
    }

    public ReviewEntity addReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("The review doesn't exist in the database!");
        }
        reviewRepository.deleteById(id);
    }
}
