package io.projeto.api.auth.application;

import io.projeto.api.auth.command.ProjetoAuthenticate;
import io.projeto.api.common.token.Token;

public interface AuthService {
    Token authenticate(ProjetoAuthenticate request);
}
