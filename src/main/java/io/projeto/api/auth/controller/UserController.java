package io.projeto.api.auth.controller;

import io.projeto.api.auth.application.UserReadModel;
import io.projeto.api.auth.presentation.UserPresentation;
import io.projeto.api.common.api.APIResponse;
import io.projeto.api.common.security.AuthRequired;
import io.projeto.api.common.security.ProjetoAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserReadModel userReadModel;

    @AuthRequired
    @GetMapping("/me")
    public APIResponse<UserPresentation> greetingAuthenticatedUser(ProjetoAuthentication authentication) {
        return APIResponse.of(userReadModel.findUser(authentication.getUserId()));
    }
}
