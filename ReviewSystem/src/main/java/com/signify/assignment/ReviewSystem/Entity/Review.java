package com.signify.assignment.ReviewSystem.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_text", nullable = false)
    private String review;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "review_source", nullable = false)
    private String reviewSource;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "reviewed_date", nullable = false)
    private LocalDateTime reviewedDate;


    public Review(String review, String author, String reviewSource, int rating, String title, String productName, LocalDateTime reviewedDate) {
        super();
        this.review = review;
        this.author = author;
        this.reviewSource = reviewSource;
        this.rating = rating;
        this.title = title;
        this.productName = productName;
        this.reviewedDate = reviewedDate;
    }

    public Review() {

    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", author='" + author + '\'' +
                ", reviewSource='" + reviewSource + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", productName='" + productName + '\'' +
                ", reviewedDate=" + reviewedDate +
                '}';
    }


    public void setReview(String review) {
        this.review = review;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setReviewedDate(LocalDateTime reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public Long getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewSource() {
        return reviewSource;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDateTime getReviewedDate() {
        return reviewedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
