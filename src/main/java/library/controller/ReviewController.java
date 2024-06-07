package library.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import library.controller.DTO.ReviewDTO.CreateReviewDTO;
import library.controller.DTO.ReviewDTO.CreateReviewResponseDTO;
import library.controller.DTO.ReviewDTO.EditReviewDTO;
import library.controller.DTO.ReviewDTO.EditReviewResponseDTO;
import library.infrastructure.entity.ReviewEntity;
import library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@Tag(name = "Review")
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<CreateReviewResponseDTO> addReview(@RequestBody CreateReviewDTO reviewDTO) {
        var newReview = reviewService.addReview(reviewDTO);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<EditReviewResponseDTO> editReview(@RequestBody EditReviewDTO reviewDTO) {
        var editedReview = reviewService.editReview(reviewDTO);
        return new ResponseEntity<>(editedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ReviewEntity getById(@PathVariable int id) {
        return reviewService.getById(id);
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<ReviewEntity> getAll() {
        return reviewService.getAll();
    }
}