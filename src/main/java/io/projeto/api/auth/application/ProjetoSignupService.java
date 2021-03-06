package io.projeto.api.auth.application;

import io.projeto.api.auth.command.ProjetoSignup;
import io.projeto.api.auth.domain.User;
import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.common.api.APIException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

    @Transactional(readOnly = true)
    public String findNicknameByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw APIException.badRequest("이메일을 입력해주세요.");
        }

        return userRepository.findByEmail(email)
                .map(User::getNickname)
                .orElse(null);
    }
}
