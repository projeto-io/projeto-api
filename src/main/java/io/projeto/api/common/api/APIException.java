package io.projeto.api.common.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException {
    private HttpStatus status;

    private APIException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static APIException userNotFound() {
        return new APIException("사용자 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }

    public static APIException projectNotFound() {
        return new APIException("프로젝트 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }

}
