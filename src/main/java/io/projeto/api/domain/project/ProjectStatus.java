package io.projeto.api.domain.project;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ProjectStatus {
    DRAFT("작성중"),
    RUNNING("진행중"),
    ARCHIVED("읽기전용"),
    DELETED("삭제됨");

    static {
        DRAFT.movableStatuses = new HashSet<>(Arrays.asList(RUNNING));
        RUNNING.movableStatuses = new HashSet<>(Arrays.asList(ARCHIVED, DELETED));
        ARCHIVED.movableStatuses = new HashSet<>(Arrays.asList(RUNNING, DELETED));
        DELETED.movableStatuses = new HashSet<>();
    }

    private String description;
    private Set<ProjectStatus> movableStatuses;


    ProjectStatus(String description) {
        this.description = description;
    }

    public boolean isChangableStatus(ProjectStatus to) {
        if (to == null) {
            return false;
        }
        return movableStatuses.contains(to);
    }
}
