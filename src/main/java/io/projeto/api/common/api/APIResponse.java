package io.projeto.api.common.api;

import lombok.Getter;

@Getter
public class APIResponse<T> {
    private String message;
    private T data;

    public APIResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> APIResponse<T> of(T data) {
        return new APIResponse<>(null, data);
    }

    public static <Void> APIResponse<Void> unSuccessful(String message) {
        return new APIResponse<>(message, null);
    }
}
