package com.lihao.bookstore.exceptions;
import org.springframework.http.HttpStatus;

public class ConstraintException extends ApiBaseException{
    public ConstraintException(String message){
        super(message);
    }
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
