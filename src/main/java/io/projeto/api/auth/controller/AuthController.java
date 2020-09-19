package io.projeto.api.auth.controller;


import io.projeto.api.auth.application.ProjetoAuthService;
import io.projeto.api.auth.application.ProjetoSignupService;
import io.projeto.api.auth.application.UserReadModel;
import io.projeto.api.auth.command.ProjetoAuthenticate;
import io.projeto.api.auth.command.ProjetoSignup;
import io.projeto.api.common.api.APIResponse;
import io.projeto.api.common.security.AuthRequired;
import io.projeto.api.common.security.ProjetoAuthentication;
import io.projeto.api.common.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ProjetoAuthService projetoAuthService;
    private final ProjetoSignupService projetoSignupService;
    private final UserReadModel userReadModel;

    @PostMapping("/signup")
    public APIResponse<Token> createUser(@Valid @RequestBody ProjetoSignup request) {
        projetoSignupService.signup(request);
        return APIResponse.of(projetoAuthService.createToken(request.getId()));
    }

    @PostMapping("/signin")
    public APIResponse<Token> authenticateUser(@Valid @RequestBody ProjetoAuthenticate request) {
        return APIResponse.of(projetoAuthService.authenticate(request));
    }


    @GetMapping("/me")
    @AuthRequired(allowGuest = true)
    public APIResponse<String> greetingAuthenticatedUser(ProjetoAuthentication authentication) {
        if (authentication.isGuest()) {
            return APIResponse.of("hello, Guest");
        }
        return APIResponse.of("hello, " + authentication.getUserId());
    }

    @GetMapping("/check")
    public APIResponse<Boolean> checkEmailAlreadyExists(@RequestParam("email") String email) {
        return APIResponse.of(projetoSignupService.checkEmailAlreadyExists(email));
    }
}
