package com.example.appdevf2.paraderooct17.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.appdevf2.paraderooct17.Entity.ReviewEntity;
import com.example.appdevf2.paraderooct17.Service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewEntity> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewEntity getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public ReviewEntity createReview(@RequestBody ReviewEntity review) {
        return reviewService.saveReview(review);
    }

    @PutMapping("/{id}")
    public ReviewEntity updateReview(@PathVariable int id, @RequestBody ReviewEntity updatedReview) {
        ReviewEntity existing = reviewService.getReviewById(id);
        if (existing != null) {
            updatedReview.setReviewId(id);
            return reviewService.saveReview(updatedReview);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
    }
}
