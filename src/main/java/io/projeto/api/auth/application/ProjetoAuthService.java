package io.projeto.api.auth.application;

import io.projeto.api.auth.command.ProjetoAuthenticate;
import io.projeto.api.auth.domain.User;
import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.common.api.APIException;
import io.projeto.api.common.token.Token;
import io.projeto.api.common.token.infra.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjetoAuthService implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional(readOnly = true)
    public Token authenticate(ProjetoAuthenticate request) {
        final String email = request.getEmail();
        final String password = request.getPassword();

        final User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.info("UserEmail '{}' not found", email);
            return APIException.userNotFound();
        });

        user.matchesPassword(password, passwordEncoder);

        return user.generateToken(jwtTokenUtil);
    }

    @Transactional(readOnly = true)
    public Token createToken(String userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> {
            log.info("UserId '{}' not found", userId);
            return APIException.userNotFound();
        });

        return user.generateToken(jwtTokenUtil);
    }
}
