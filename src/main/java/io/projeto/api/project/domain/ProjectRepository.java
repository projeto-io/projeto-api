package io.projeto.api.project.domain;

import java.util.Optional;

public interface ProjectRepository {
    Project save(Project project);

    Optional<Project> findById(String id);
}
