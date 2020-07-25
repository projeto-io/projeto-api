package io.projeto.api.project.application;

import io.projeto.api.common.api.APIException;
import io.projeto.api.project.domain.Project;
import io.projeto.api.project.domain.ProjectRepository;
import io.projeto.api.project.presentation.ProjectPresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectReadModel {
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ProjectPresentation findByProjectId(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> {
                    log.info("ProjectId '{}' not found", projectId);
                    return APIException.projectNotFound();
                });

        return ProjectPresentation.of(project);
    }
}
