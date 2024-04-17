package library.service;

import library.controller.DTO.ReviewDTO.CreateReviewDTO;
import library.controller.DTO.ReviewDTO.CreateReviewResponseDTO;
import library.controller.DTO.ReviewDTO.EditReviewDTO;
import library.controller.DTO.ReviewDTO.EditReviewResponseDTO;
import library.exception.BookNotFound;
import library.exception.RatingOverScale;
import library.exception.ReviewNotFound;
import library.exception.UserNotFound;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.ReviewEntity;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.BookRepository;
import library.infrastructure.repository.ReviewRepository;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<ReviewEntity> getAll() {
        return reviewRepository.findAll();
    }

    public ReviewEntity getOne(int id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found!"));
    }

    @Transactional
    public CreateReviewResponseDTO addReview(CreateReviewDTO reviewDTO) {
        BookEntity book = bookRepository.findById(reviewDTO.getBookId()).orElseThrow(() -> BookNotFound.create(reviewDTO.getBookId()));
        UserEntity user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> UserNotFound.create(reviewDTO.getUserId()));
        ReviewEntity review = new ReviewEntity();
        review.setBook(book);
        review.setUser(user);
        if (reviewDTO.getRating() > 10) throw RatingOverScale.create();
        review.setRating(reviewDTO.getRating());
        review.setReviewDate(new Date(System.currentTimeMillis()));
        review.setComment(reviewDTO.getComment());
        var newReview = reviewRepository.save(review);
        return new CreateReviewResponseDTO(newReview.getId(), newReview.getBook(), newReview.getUser(), newReview.getRating(), newReview.getComment(), newReview.getReviewDate());
    }

    public void deleteReview(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("The review doesn't exist in the database!");
        }
        reviewRepository.deleteById(id);
    }

    public EditReviewResponseDTO editReview(EditReviewDTO reviewDTO) {
        ReviewEntity review = reviewRepository.findById(reviewDTO.getId()).orElseThrow(() -> ReviewNotFound.create(reviewDTO.getId()));
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setReviewDate(new Date(System.currentTimeMillis()));
        reviewRepository.save(review);
        return new EditReviewResponseDTO(review.getId(), review.getBook(), review.getUser(), reviewDTO.getRating(), reviewDTO.getComment(), review.getReviewDate());
    }
}
