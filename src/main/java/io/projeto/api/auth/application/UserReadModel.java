package io.projeto.api.auth.application;

import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.auth.presentation.UserPresentation;
import io.projeto.api.common.api.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReadModel {
    private final UserRepository userRepository;

    public UserPresentation findUser(String userId) {
        return userRepository.findById(userId)
                .map(UserPresentation::of)
                .orElseThrow(() -> {
                    log.info("UserId '{}' not found", userId);
                    return APIException.userNotFound();
                });
    }
}
