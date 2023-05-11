package com.mju.Review.presentation.dto;

import com.mju.Review.domain.model.ReviewComplaint;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewComplaintDto {
    private String complaintContent;
    private ReviewComplaint.ComplaintType type;
}
