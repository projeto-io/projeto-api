package io.projeto.api.auth.application;

import io.projeto.api.auth.command.ProjetoSignup;
import io.projeto.api.auth.domain.User;
import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.common.api.APIException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjetoSignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(ProjetoSignup request) {
        User user = new User(
                request.getId(),
                request.getName(),
                request.getNickname(),
                request.getEmail(),
                request.getPassword(),
                request.getTelephone(),
                passwordEncoder
        );

        if (userRepository.existsByEmail(user.getEmail())) {
            throw APIException.emailDuplicated();
        }

        if (userRepository.existsByNickname(user.getNickname())) {
            throw APIException.nicknameDuplicated();
        }

        if (userRepository.existsByTelephone(user.getTelephone())) {
            throw APIException.telephoneDuplicated();
        }

        userRepository.save(user);
    }
}
