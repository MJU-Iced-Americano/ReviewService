package com.mju.Review.domain.model.other.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    UNKNOWN(-9999, "알 수 없는 오류가 발생하였습니다."),
    EMPTY_USER(-5051, "유저 정보를 입력해 주세요."),
    NOT_CORRECT_USER(-5052, "수강생이 아닙니다. 수강생으로 로그인 다시 부탁드립니다."),
    NOT_ACCESS_USER(-5053, "접근할 수 없는 유저 입니다."),
    EMPTY_JWT(-5054, "토큰이 없습니다. 확인부탁드립니다."),
    NOT_EXISTENT_USER(-5055, "존재하지 않는 유저입니다."),

    ALREADY_LIKED_USER(-2222, "이미 좋아요를 누른 계정입니다."),
    ALREADY_DELIKED_USER(-2222, "이미 싫어요를 누른 계정입니다."),

    NOT_EXISTENT_REVIEW(-5005, "존재하지 않는 리뷰입니다."),
    NOT_EXISTENT_FIND_REVIEW(-5004, "리뷰 목록이 없습니다."),
    NOT_EXISTENT_FILL_REVIEW(-5003, "존재하지 않은 값을 불러오고 있습니다.");
    private final int code;
    private final String message;
}
