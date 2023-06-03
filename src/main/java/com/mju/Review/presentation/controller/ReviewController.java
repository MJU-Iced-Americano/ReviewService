package com.mju.Review.presentation.controller;

import com.mju.Review.appliocation.ReviewService;
import com.mju.Review.appliocation.UserServiceImpl;
import com.mju.Review.domain.model.Review;
import com.mju.Review.domain.model.other.Exception.ExceptionList;
import com.mju.Review.domain.model.other.Exception.ReviewNotFindException;
import com.mju.Review.domain.model.other.Result.CommonResult;
import com.mju.Review.domain.service.ResponseService;
import com.mju.Review.presentation.dto.ReviewRegisterDto;
import com.mju.Review.presentation.dto.UserInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/review-service")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserServiceImpl userService;

    private final ResponseService responseService;

    @GetMapping("/review/get/{course_index}")
    public CommonResult getReview(@PathVariable Long course_index) {
        List<Review> ReviewList = reviewService.getReview(course_index);
        List<Review> ReviewUserList = ReviewUsername(ReviewList);
        CommonResult commonResult = responseService.getListResult(ReviewUserList);
        return commonResult;
    }

    private List<Review> ReviewUsername(List<Review> reviewList) {
        for (Review review : reviewList){
            String userId = review.getUserId();
            userService.checkUserId(userId);
            UserInfoDto userInfoDto = userService.getUserInfoDto(userId);
            String userName = userInfoDto.getUsername();
            review.addUserName(userName);
        }
        return reviewList;
    }


    @PostMapping("/review/register/{course_index}")
    public CommonResult registerReview(@PathVariable Long course_index, @RequestBody ReviewRegisterDto reviewRegisterDto, HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserType(userId, "STUDENT");
        reviewService.registerReview(course_index,userId, reviewRegisterDto);
        return responseService.getSuccessfulResult();
    }

    @DeleteMapping("review/delete/{course_index}")
    public CommonResult deleteReview(@PathVariable Long course_index, HttpServletRequest request) throws Exception {
        String userId = userService.getUserId(request);
        userService.checkUserType(userId, "TEACHER");
        reviewService.deleteReview(course_index);
        return responseService.getSuccessfulResult();
    }

    // 좋아요 수
    // 좋아요 증가
    @GetMapping("/review/like/{review_index}")
    public CommonResult incrementLiked(@PathVariable Long review_index, HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserType(userId, "STUDENT");

        boolean alreadyLiked = reviewService.checkIfAlreadyLikedReview(review_index, userId);
        if (alreadyLiked) {
            throw new ReviewNotFindException(ExceptionList.ALREADY_LIKED_USER);
        }
        reviewService.incrementLiked(userId, review_index);
        return responseService.getSuccessfulResult();
    }

    // 좋아요 감소
    @GetMapping("/review/delike/{review_index}")
    public CommonResult decrementLiked(@PathVariable Long review_index, HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserType(userId, "STUDENT");

        boolean alreadyDeliked = reviewService.checkIfAlreadyDelikedReview(review_index, userId);
        if (alreadyDeliked) {
            throw new ReviewNotFindException(ExceptionList.ALREADY_DELIKED_USER);
        }
        reviewService.decrementLiked(userId, review_index);
        return responseService.getSuccessfulResult();
    }

    // 필터링
    // 높은 평점 순
    @GetMapping("/review/getDgrade")
    public CommonResult getDGradeReview() {
        List<Review> ReviewList = reviewService.getDGradeReview();
        CommonResult commonResult = responseService.getListResult(ReviewList);
        return commonResult;
    }

    // 낮은 평점 순
    @GetMapping("/review/getAgrade")
    public CommonResult getAGradeReview() {
        List<Review> ReviewList = reviewService.getAGradeReview();
        CommonResult commonResult = responseService.getListResult(ReviewList);
        return commonResult;
    }

    // 최신 순
    @GetMapping("/review/getDate")
    public CommonResult getDate() {
        List<Review> ReviewList = reviewService.getDate();
        CommonResult commonResult = responseService.getListResult(ReviewList);
        return commonResult;
    }

    // 좋아요 순
    @GetMapping("/review/getLiked")
    public CommonResult getLiked() {
        List<Review> ReviewList = reviewService.getLiked();
        CommonResult commonResult = responseService.getListResult(ReviewList);
        return commonResult;
    }
}
