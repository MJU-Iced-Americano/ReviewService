package com.mju.Review.presentation.controller;

import com.mju.Review.appliocation.ReviewService;
import com.mju.Review.domain.model.Review;
import com.mju.Review.domain.model.other.Result.SingleResult;
import com.mju.Review.domain.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/review-service")
public class RequestController {
    private final ReviewService reviewService;
    private final ResponseService responseService;

    @GetMapping("/review/show/request/{review_index}")
    public SingleResult reviewFindById(@PathVariable long review_index){
        Review review = reviewService.getReviewOne(review_index);
        SingleResult requestResult = responseService.getSingleResult(review);
        return requestResult;
    }
}
