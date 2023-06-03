package com.mju.Review.appliocation;

import com.mju.Review.domain.model.Review;
import com.mju.Review.domain.model.other.Exception.ExceptionList;
import com.mju.Review.domain.model.other.Exception.ReviewNotFindException;
import com.mju.Review.domain.repository.ReviewRepository;
import com.mju.Review.presentation.controller.CourseFeignClient;
import com.mju.Review.presentation.dto.ClientReadCourseDto;
import com.mju.Review.presentation.dto.ReviewRegisterDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseFeignClient courseFeignClient;
    @Override
    @Transactional
    public List<Review> getReview(Long courseIndex) {
        List<Review> reviewList = reviewRepository.findByCourseIndex(courseIndex);
        if (!reviewList.isEmpty()) {
            return reviewList;
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_FIND_REVIEW);
        }
    }

    @Override
    @Transactional
    public void registerReview(Long course_index, String userId, ReviewRegisterDto reviewRegisterDto) {
        Review review = new Review(reviewRegisterDto.getGrade(), reviewRegisterDto.getReview_content());
        ClientReadCourseDto clientReadCourseDto = courseFeignClient.courseFindById(course_index);

        if (clientReadCourseDto != null && reviewRegisterDto.getGrade() != null && reviewRegisterDto.getReview_content() != null) {
            Review newreview = Review.builder()
                    .grade(review.getGrade())
                    .user_id(userId)
                    .date(review.getDate())
                    .review_content(review.getReview_content())
                    .likes(review.getLikes())
                    .courseIndex(course_index)
                    .build();
            reviewRepository.save(newreview);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_FILL_REVIEW);
        }
    }

    @Override
    @Transactional
    public void deleteReview(Long course_index) {
        List<Review> reviewList = reviewRepository.findByCourseIndex(course_index);
        if(!reviewList.isEmpty()) {
            reviewRepository.deleteByCourseIndex(course_index);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_FIND_REVIEW);
        }
    }

    @Override
    @Transactional
    public boolean checkIfAlreadyLikedReview(Long review_index, String userId) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()){
            Review review = optionalReview.get();
            List<String> likeUserIds = review.getLikedUserIds();
            return likeUserIds.contains(userId);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_REVIEW);
        }
    }

    @Override
    @Transactional
    public void incrementLiked(String userId, Long review_index) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            if (checkIfAlreadyLikedReview(review_index, userId)) {
                throw new ReviewNotFindException(ExceptionList.ALREADY_LIKED_USER);
            }
            review.addLikedUserId(userId);
            review.incrementLike();
            reviewRepository.save(review);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_REVIEW);
        }
    }

    @Override
    @Transactional
    public boolean checkIfAlreadyDelikedReview(Long review_index, String userId) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()){
            Review review = optionalReview.get();
            List<String> DelikeUserIds = review.getDelikedUserIds();
            return DelikeUserIds.contains(userId);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_REVIEW);
        }
    }

    @Override
    @Transactional
    public void decrementLiked(String userId, Long review_index) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            if (checkIfAlreadyDelikedReview(review_index, userId)) {
                throw new ReviewNotFindException(ExceptionList.ALREADY_DELIKED_USER);
            }
            review.addDelikedUserId(userId);
            review.decrementLike();
            reviewRepository.save(review);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_REVIEW);
        }
    }



    @Override
    @Transactional
    public List<Review> getDGradeReview() {
        List<Review> ReviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "grade"));
        return ReviewList;
    }

    @Override
    @Transactional
    public List<Review> getAGradeReview() {
        List<Review> ReviewList = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "grade"));
        return ReviewList;
    }

    @Override
    @Transactional
    public List<Review> getDate() {
        List<Review> ReviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        return ReviewList;
    }

    @Override
    @Transactional
    public List<Review> getLiked() {
        List<Review> ReviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "likes"));
        return ReviewList;
    }

    @Override
    @Transactional
    public Review getReviewOne(Long review_index) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            return review;
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_REVIEW);
        }
    }
}
