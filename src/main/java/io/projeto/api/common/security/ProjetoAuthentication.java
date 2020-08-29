package io.projeto.api.common.security;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class ProjetoAuthentication {
    public static ProjetoAuthentication GUEST = new ProjetoAuthentication(null, true);
    private String userId;
    private boolean isGuest;

    private ProjetoAuthentication(String userId, boolean isGuest) {
        this.userId = userId;
        this.isGuest = isGuest;
    }

    public static ProjetoAuthentication of(String userId) {
        if (Objects.isNull(userId)) {
            return GUEST;
        }
        return new ProjetoAuthentication(userId, false);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetoAuthentication that = (ProjetoAuthentication) o;
        return isGuest == that.isGuest &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, isGuest);
    }
}
