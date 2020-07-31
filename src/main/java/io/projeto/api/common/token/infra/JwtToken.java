package io.projeto.api.common.token.infra;

import io.projeto.api.common.token.Token;
import lombok.Getter;

@Getter
public class JwtToken implements Token {
    private String accessToken;
    private String refreshToken;

    public JwtToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
