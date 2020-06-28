package io.projeto.api.project.controller;

import io.projeto.api.project.application.ProjectReadModel;
import io.projeto.api.project.application.ProjectService;
import io.projeto.api.project.command.ProjectCreate;
import io.projeto.api.project.presentation.ProjectPresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectReadModel projectReadModel;

    @PostMapping
    public ProjectPresentation createProject(@RequestBody ProjectCreate request) {
        projectService.createProject(request);

        return projectReadModel.findByProjectId(request.getId());
    }

}
