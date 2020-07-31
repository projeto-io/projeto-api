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

    public static APIException passwordMismatch() {
        return new APIException("비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
    }

    public static APIException unauthenticated() {
        return new APIException("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
    }

    public static APIException tokenExpired() {
        return new APIException("만료된 토큰입니다.", HttpStatus.UNAUTHORIZED);
    }

    public static APIException tokenNotValid() {
        return new APIException("올바르지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);
    }
}
