package io.projeto.api.project.application;

import io.projeto.api.project.domain.Project;
import io.projeto.api.project.domain.ProjectRepository;
import io.projeto.api.project.presentation.ProjectPresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectReadModel {
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ProjectPresentation findByProjectId(String projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(IllegalArgumentException::new);

        return ProjectPresentation.of(project);
    }
}
