package io.projeto.api.project.infra;

import io.projeto.api.project.domain.Project;
import io.projeto.api.project.domain.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAProjectRepository extends ProjectRepository, JpaRepository<Project, String> {
}
