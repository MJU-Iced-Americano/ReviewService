package com.mju.Review.domain.service;

import com.mju.Review.domain.model.other.Exception.ExceptionList;
import com.mju.Review.domain.model.other.Exception.ReviewNotFindException;
import com.mju.Review.domain.model.other.Result.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionService {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult unknown(Exception e){
        log.error("unknown exception", e);
        return responseService.getFailResult(ExceptionList.UNKNOWN.getCode(), ExceptionList.UNKNOWN.getMessage());
    }

    @ExceptionHandler({ReviewNotFindException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult handleCustom(ReviewNotFindException e){
        log.error("Review not found", e);
        ExceptionList exceptionList = e.getExceptionList();
        return responseService.getFailResult(exceptionList.getCode(), exceptionList.getMessage());
    }

}
