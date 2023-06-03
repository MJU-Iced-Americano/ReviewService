package com.mju.Review.presentation.controller;

import com.mju.Review.presentation.dto.ClientReadCourseDto;
import com.mju.Review.presentation.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service", url = "http://54.180.213.187:8080")
public interface CourseFeignClient {
    @GetMapping("/client/course/{course_index}")
    ClientReadCourseDto courseFindById(@PathVariable Long course_index);
}
