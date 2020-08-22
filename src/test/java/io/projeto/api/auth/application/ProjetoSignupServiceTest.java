package io.projeto.api.auth.application;

import io.projeto.api.auth.command.ProjetoSignup;
import io.projeto.api.auth.domain.User;
import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.common.api.APIException;
import io.projeto.api.common.util.UUIDGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("회원가입 >")
@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProjetoSignupServiceTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    private ProjetoSignupService service;

    @BeforeEach
    public void setup() {
        userRepository.save(new User(
                UUIDGenerator.nextUUID(),
                "찬명",
                "nickname",
                "someemail@gmail.com",
                "password123!@#",
                "01012345678",
                passwordEncoder
        ));
        this.service = new ProjetoSignupService(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("동일한 이메일로는 가입이 불가능하다")
    public void signupFailsWhenEmailHasTaken() {
        ProjetoSignup request = new ProjetoSignup(UUIDGenerator.nextUUID(), "someemail@gmail.com", "password123!@#", "찬명2", "nickname2", "01087654321");
        assertThrows(APIException.class, () -> service.signup(request), "이미 사용중인 이메일입니다.");
    }

    @Test
    @DisplayName("동일한 닉네임은 사용할 수 없다.")
    public void signupFailsWhenNicknameHasTaken() {
        ProjetoSignup request = new ProjetoSignup(UUIDGenerator.nextUUID(), "differentEmail@gmail.com", "password123!@#", "찬명2", "nickname", "01087654321");
        assertThrows(APIException.class, () -> service.signup(request), "이미 사용중인 닉네임입니다.");
    }

    @Test
    @DisplayName("동일한 전화번호를 사용할 수 없다.")
    public void signupFailsWhenTelephoneHasTaken() {
        ProjetoSignup request = new ProjetoSignup(UUIDGenerator.nextUUID(), "differentEmail@gmail.com", "password123!@#", "찬명2", "nickname2", "01012345678");
        assertThrows(APIException.class, () -> service.signup(request), "이미 사용중인 전화번호입니다.");
    }

    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }
}