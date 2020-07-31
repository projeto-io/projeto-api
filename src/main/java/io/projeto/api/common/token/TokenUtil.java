package io.projeto.api.common.token;

public interface TokenUtil {
    Token generateToken(String userId);

    String validate(Token token);

    String validate(String accessToken);

    String getHeaderPrefix();
}
