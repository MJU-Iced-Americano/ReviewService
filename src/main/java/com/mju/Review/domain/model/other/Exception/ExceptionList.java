package com.mju.Review.domain.model.other.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    UNKNOWN(-9999, "알 수 없는 오류가 발생하였습니다."),
    NOT_EXISTENT_REVIEW(-5005, "존재하지 않는 리뷰입니다."),
    NOT_EXISTENT_FIND_REVIEW(-5004, "리뷰 목록이 없습니다."),
    NOT_EXISTENT_FILL_REVIEW(-5003, "빈칸을 입력해주세요.");
    private final int code;
    private final String message;
}
