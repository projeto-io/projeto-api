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

    public static APIException badRequest(String message) {
        return new APIException(message, HttpStatus.BAD_REQUEST);
    }

    public static APIException nicknameDuplicated() {
        return new APIException("이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST);
    }

    public static APIException telephoneDuplicated() {
        return new APIException("이미 사용중인 전화번호입니다.", HttpStatus.BAD_REQUEST);
    }

    public static APIException emailDuplicated() {
        return new APIException("이미 사용중인 이메일입니다.", HttpStatus.BAD_REQUEST);
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
