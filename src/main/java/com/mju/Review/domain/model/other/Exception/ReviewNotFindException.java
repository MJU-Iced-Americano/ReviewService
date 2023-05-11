package com.mju.Review.domain.model.other.Exception;

public class ReviewNotFindException extends RuntimeException{

    private final ExceptionList exceptionList;

    public ReviewNotFindException(ExceptionList exceptionList){
        super(exceptionList.getMessage());
        this.exceptionList = exceptionList;
    }

    public ExceptionList getExceptionList() {
        return exceptionList;
    }
}
