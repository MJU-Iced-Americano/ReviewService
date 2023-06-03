package com.mju.Review.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRegisterDto {
    private Long grade;
    private String review_content;
}
