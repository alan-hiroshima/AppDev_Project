package com.example.appdevf2.paraderooct17.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appdevf2.paraderooct17.Entity.ReviewEntity;
import com.example.appdevf2.paraderooct17.Repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ReviewEntity getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public ReviewEntity saveReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
