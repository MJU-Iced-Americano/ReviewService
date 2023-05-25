package com.mju.Review.appliocation;

import com.mju.Review.domain.model.Review;
import com.mju.Review.presentation.dto.ReviewRegisterDto;

import java.util.List;

public interface ReviewService {
    List<Review> getReview();

    void registerReview(ReviewRegisterDto reviewRegisterDto);

    void deleteReview(Long review_index);

    void incrementLiked(Long review_index);

    void decrementLiked(Long review_index);

    List<Review> getDGradeReview();

    List<Review> getAGradeReview();

    List<Review> getDate();

    List<Review> getLiked();

    public Review getReviewOne(Long review_index);
}
