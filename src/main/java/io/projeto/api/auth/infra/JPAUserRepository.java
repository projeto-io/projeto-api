package io.projeto.api.auth.infra;

import io.projeto.api.auth.domain.User;
import io.projeto.api.auth.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserRepository extends UserRepository, JpaRepository<User, String> {
}
