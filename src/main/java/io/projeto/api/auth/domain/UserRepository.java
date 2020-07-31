package io.projeto.api.auth.domain;


import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String userId);
}
