package com.signify.assignment.ReviewSystem.Service;

import com.signify.assignment.ReviewSystem.Entity.Review;
import com.signify.assignment.ReviewSystem.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReviewService  {
    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepo) {
        reviewRepository = reviewRepo;
    }
    public List<Review> getReviews(Optional<String> reviewSource, Optional<Integer> rating, Optional<LocalDateTime> fromDate, Optional<LocalDateTime> toDate) {
        if (!reviewSource.isPresent() && !rating.isPresent() && !fromDate.isPresent() && !toDate.isPresent()) {
            return reviewRepository.findAll();
        }
        if (reviewSource.isPresent() && rating.isPresent() && !fromDate.isPresent() && !toDate.isPresent()) {
            return reviewRepository.findByReviewSourceAndRating(reviewSource.get(), rating.get());
        }
        if (!reviewSource.isPresent() && !rating.isPresent() && fromDate.isPresent() && toDate.isPresent()) {
            return reviewRepository.findByReviewedDateBetween(fromDate.get(), toDate.get());
        }
        return new ArrayList<>();
    }


    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Double getAverageMonthlyRatingForStore(String reviewSource, int month) {
        return reviewRepository.findAverageMonthlyRatingByStore(reviewSource, month);
    }

    public Map<Integer, Long> getTotalRatingsPerCategory() {
        List<Object[]> results = reviewRepository.countRatingsGroupedByRating();
        Map<Integer, Long> resultMap = new HashMap<>();

        for (Object[] result : results) {
            resultMap.put((Integer) result[0], (Long) result[1]);
        }

        return resultMap;
    }
}
