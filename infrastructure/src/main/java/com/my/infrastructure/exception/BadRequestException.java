package com.my.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Request")
@RequiredArgsConstructor
public class BadRequestException extends RuntimeException{

    private final String message;

}
