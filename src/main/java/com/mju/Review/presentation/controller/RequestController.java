package com.mju.Review.presentation.controller;

import com.mju.Review.appliocation.ReviewService;
import com.mju.Review.appliocation.UserServiceImpl;
import com.mju.Review.domain.model.Review;
import com.mju.Review.domain.model.other.Result.SingleResult;
import com.mju.Review.domain.service.ResponseService;
import com.mju.Review.presentation.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/review-service")
public class RequestController {
    private final ReviewService reviewService;
    private final UserServiceImpl userService;
    private final ResponseService responseService;

    @GetMapping("/review/show/request/{review_index}")
    public SingleResult reviewFindById(@PathVariable long review_index){
        Review review = reviewService.getReviewOne(review_index);
        userService.checkUserId(review.getUserId());
        UserInfoDto userInfoDto = userService.getUserInfoDto(review.getUserId());
        review.addNickName(userInfoDto.getNickname());
        SingleResult requestResult = responseService.getSingleResult(review);
        return requestResult;
    }
}
