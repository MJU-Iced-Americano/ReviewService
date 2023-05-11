package com.mju.Review.presentation.controller;

import com.mju.Review.appliocation.ReviewService;
import com.mju.Review.domain.model.Review;
import com.mju.Review.domain.model.other.Result.CommonResult;
import com.mju.Review.domain.service.ResponseService;
import com.mju.Review.presentation.dto.ReviewComplaintDto;
import com.mju.Review.presentation.dto.ReviewRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/review-service")
public class ReviewController {

    private final ReviewService reviewService;
    private final ResponseService responseService;

    @GetMapping("/review/get")
    public CommonResult getReview() {
        List<Review> ReviewList = reviewService.getReview();
        CommonResult commonResult = responseService.getListResult(ReviewList);
        return commonResult;
    }

    @PostMapping("/review/register")
    public CommonResult registerReview(@RequestBody ReviewRegisterDto reviewRegisterDto) {
        reviewService.registerReview(reviewRegisterDto);
        return responseService.getSuccessfulResult();
    }

    // 리뷰 신고
    @PostMapping("/review/complaint/{review_index}")
    public CommonResult complaint(@PathVariable Long review_index, @RequestBody ReviewComplaintDto reviewComplaintDto){
        reviewService.complaint(review_index, reviewComplaintDto);
        return responseService.getSuccessfulResult();
    }

    // 회원용, 운영자용 리뷰 삭제
    @DeleteMapping("/review/delete/{review_index}")
    public CommonResult deleteReview(@PathVariable Long review_index) {
        reviewService.deleteReview(review_index);
        return responseService.getSuccessfulResult();
    }

    // 좋아요 수
    // 좋아요 증가
    @GetMapping("/review/inlike/{review_index}")
    public CommonResult incrementLiked(@PathVariable Long review_index) {
        reviewService.incrementLiked(review_index);
        return responseService.getSuccessfulResult();
    }

    // 좋아요 감소
    @GetMapping("/review/delike/{review_index}")
    public CommonResult decrementLiked(@PathVariable Long review_index) {
        reviewService.decrementLiked(review_index);
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
