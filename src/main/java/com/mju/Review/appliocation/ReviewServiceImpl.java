package com.mju.Review.appliocation;

import com.mju.Review.domain.model.Review;
import com.mju.Review.domain.model.other.Exception.ExceptionList;
import com.mju.Review.domain.model.other.Exception.ReviewNotFindException;
import com.mju.Review.domain.repository.ReviewRepository;
import com.mju.Review.presentation.dto.ReviewRegisterDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public List<Review> getReview() {
        List<Review> ReviewList = reviewRepository.findAll();
        if (!ReviewList.isEmpty()) {
            return ReviewList;
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_FIND_REVIEW);
        }
    }

    @Override
    @Transactional
    public void registerReview(ReviewRegisterDto reviewRegisterDto) {
        Review review = new Review(reviewRegisterDto.getGrade(), reviewRegisterDto.getUser_photo(), reviewRegisterDto.getUser_name(), reviewRegisterDto.getReview_content());
        if (reviewRegisterDto.getGrade() != null && reviewRegisterDto.getUser_photo() != null && reviewRegisterDto.getUser_name() != null && reviewRegisterDto.getReview_content() != null) {
            Review newreview = Review.builder()
                    .grade(review.getGrade())
                    .user_photo(review.getUser_photo())
                    .user_name(review.getUser_name())
                    .date(review.getDate())
                    .review_content(review.getReview_content())
                    .likes(review.getLikes())
                    .build();
            reviewRepository.save(newreview);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_FILL_REVIEW);
        }
    }

    @Override
    @Transactional
    public void deleteReview(Long review_index) {
        Optional<Review> Review = reviewRepository.findById(review_index);
        if (Review.isPresent()) {
            reviewRepository.deleteById(review_index);
        } else {
            throw new ReviewNotFindException(ExceptionList.NOT_EXISTENT_REVIEW);
        }
    }

    @Override
    @Transactional
    public void incrementLiked(Long review_index) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.incrementLike();
            reviewRepository.save(review);
        }
    }

    @Override
    @Transactional
    public void decrementLiked(Long review_index) {
        Optional<Review> optionalReview = reviewRepository.findById(review_index);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.decrementLike();
            reviewRepository.save(review);
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
