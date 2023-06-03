package com.mju.Review.presentation.controller;

import com.mju.Review.presentation.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign", url = "52.79.197.136")
public interface UserFeignClient {
    @GetMapping("/user/response_user/{userId}")
    UserInfoDto getUserInfo(@PathVariable("userId") String userId);
}
