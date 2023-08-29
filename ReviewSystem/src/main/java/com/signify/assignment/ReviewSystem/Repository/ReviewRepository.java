package com.signify.assignment.ReviewSystem.Repository;

import com.signify.assignment.ReviewSystem.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByReviewSourceAndRating(String reviewSource, int rating);
    List<Review> findByReviewedDateBetween(LocalDateTime start, LocalDateTime end);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.reviewSource = :reviewSource AND MONTH(r.reviewedDate) = :month")
    Double findAverageMonthlyRatingByStore(@Param("reviewSource") String reviewSource, @Param("month") int month);
    @Query("SELECT r.rating, COUNT(r) FROM Review r GROUP BY r.rating")
    List<Object[]> countRatingsGroupedByRating();

}
