package com.mju.Review.appliocation;

import com.mju.Review.domain.model.Review;
import com.mju.Review.presentation.dto.ReviewRegisterDto;

import java.util.List;

public interface ReviewService {
    List<Review> getReview(Long course_index);

    void registerReview(Long course_index, String userId, ReviewRegisterDto reviewRegisterDto);

    void deleteReview(Long course_index);
    void incrementLiked(String userId, Long review_index);

    boolean checkIfAlreadyLikedReview(Long review_index, String userId);

    void decrementLiked(String userId, Long review_index);
    boolean checkIfAlreadyDelikedReview(Long review_index, String userId);

    List<Review> getDGradeReview(Long course_index);

    List<Review> getAGradeReview(Long course_index);

    List<Review> getDate(Long course_index);

    List<Review> getLiked(Long course_index);

    public Review getReviewOne(Long review_index);
}
