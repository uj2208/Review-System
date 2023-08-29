package com.signify.assignment.ReviewSystem.Controller;

import com.signify.assignment.ReviewSystem.Entity.Review;
import com.signify.assignment.ReviewSystem.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService1) {
        this.reviewService = reviewService1;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam(required = false) String reviewSource, @RequestParam(required = false) Integer rating, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
        return new ResponseEntity<>(reviewService.getReviews(Optional.ofNullable(reviewSource), Optional.ofNullable(rating), Optional.ofNullable(fromDate), Optional.ofNullable(toDate)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.addReview(review), HttpStatus.CREATED);
    }

    @GetMapping("/averageMonthlyRating")
    public ResponseEntity<Double> getAverageMonthlyRating(@RequestParam String reviewSource, @RequestParam int month) {
        return new ResponseEntity<>(reviewService.getAverageMonthlyRatingForStore(reviewSource, month), HttpStatus.OK);
    }

    @GetMapping("/ratingsCount")
    public ResponseEntity<Map<Integer, Long>> getTotalRatingsPerCategory() {
        return new ResponseEntity<>(reviewService.getTotalRatingsPerCategory(), HttpStatus.OK);
    }

}
