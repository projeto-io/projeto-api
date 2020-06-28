package io.projeto.api.project.presentation;

import io.projeto.api.project.domain.Project;
import lombok.Getter;

@Getter
public class ProjectPresentation {
    private String projectId;

    private String name;

    private String description;

    private String explanation;

    private ProjectPresentation(String projectId, String name, String description, String explanation) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.explanation = explanation;
    }

    public static ProjectPresentation of(Project project) {
        return new ProjectPresentation(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getExplanation()
        );
    }
}
