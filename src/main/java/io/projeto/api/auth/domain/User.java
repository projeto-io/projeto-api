package io.projeto.api.auth.domain;

import io.projeto.api.common.api.APIException;
import io.projeto.api.common.token.Token;
import io.projeto.api.common.token.TokenUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Getter
@Table(name = "user")
@Entity(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserSocialLogin> socialLogins;
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "telephone_certified", nullable = false)
    private Boolean telephoneCertified;


    public User(String id, String name, String nickname, String email, String password, String telephone, PasswordEncoder passwordEncoder) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.telephone = telephone;
        this.telephoneCertified = false;
    }

    public User(String id, String name, String nickname, String email, String password, String telephone, List<UserSocialLogin> socialLogins, PasswordEncoder passwordEncoder) {
        this(id, name, nickname, email, password, telephone, passwordEncoder);
        this.socialLogins = socialLogins;
    }

    public void matchesPassword(String password, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw APIException.passwordMismatch();
        }
    }

    public Token generateToken(TokenUtil tokenUtil) {
        return tokenUtil.generateToken(id);
    }
}
