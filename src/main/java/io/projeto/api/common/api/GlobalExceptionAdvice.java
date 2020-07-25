package io.projeto.api.common.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> handleAPIException(APIException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(APIResponse.unSuccessful(exception.getMessage()));
    }
}
