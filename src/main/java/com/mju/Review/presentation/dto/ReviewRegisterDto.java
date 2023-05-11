package com.mju.Review.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRegisterDto {
    private Long grade;
    private String user_photo;
    private String user_name;
    private String review_content;
}
