package library.controller;

import library.controller.DTO.ReviewDTO.CreateReviewDTO;
import library.controller.DTO.ReviewDTO.CreateReviewResponseDTO;
import library.controller.DTO.ReviewDTO.EditReviewDTO;
import library.controller.DTO.ReviewDTO.EditReviewResponseDTO;
import library.infrastructure.entity.ReviewEntity;
import library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<CreateReviewResponseDTO> addReview(@RequestBody CreateReviewDTO review) {
        var newReview = reviewService.addReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<EditReviewResponseDTO> editReview(@RequestBody EditReviewDTO review) {
        var editedReview = reviewService.editReview(review);
        return new ResponseEntity<>(editedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ReviewEntity getOne(@PathVariable int id) {
        return reviewService.getOne(id);
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<ReviewEntity> getAll() {
        return reviewService.getAll();
    }
}