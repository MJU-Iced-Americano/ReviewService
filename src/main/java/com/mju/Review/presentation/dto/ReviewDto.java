package com.mju.Review.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewDto {
    private Long grade;
    private String user_photo;
    private String user_name;
    private LocalDateTime date;
    private String review_content;
    private Long likes;
}
