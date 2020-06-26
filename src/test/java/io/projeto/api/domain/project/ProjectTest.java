package io.projeto.api.domain.project;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("프로젝트 >")
class ProjectTest {

    @Test
    @DisplayName("프로젝트는 3자 이상 20자 이하의 프로젝트명을 가져야한다.")
    void projectMustHaveName() {

        assertThrows(IllegalArgumentException.class, () -> constructWithName(null));

        assertThrows(IllegalArgumentException.class, () -> constructWithName(""));

        assertThrows(IllegalArgumentException.class, () -> constructWithName("  "));

        assertThrows(IllegalArgumentException.class, () -> constructWithName(StringUtils.repeat("*", 2)));

        assertThrows(IllegalArgumentException.class, () -> constructWithName(StringUtils.repeat("*", 21)));

        assertDoesNotThrow(() -> {
            constructWithName(StringUtils.repeat("*", 3));
            constructWithName(StringUtils.repeat("*", 20));
        });
    }

    private void constructWithName(String name) {
        new Project(name, "description", "introduction", null, null, null, null, null);
    }
}