package com.signify.assignment.ReviewSystem.Controller;

import com.signify.assignment.ReviewSystem.Entity.Review;
import com.signify.assignment.ReviewSystem.Service.ReviewService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void testGetReviews() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        Review review = new Review();
        review.setId(1L);
        review.setAuthor("TestAuthor");
        review.setRating(5);
        review.setReviewedDate(now);
        review.setReviewSource("iTunes");

        List<Review> mockReviews = Arrays.asList(review);

        Mockito.when(reviewService.getReviews(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty())).thenReturn(mockReviews);

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("TestAuthor")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating", Matchers.is(5)));
    }
}
