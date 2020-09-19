package io.projeto.api.project.controller;

import io.projeto.api.common.api.APIResponse;
import io.projeto.api.common.security.AuthRequired;
import io.projeto.api.common.security.ProjetoAuthentication;
import io.projeto.api.project.application.ProjectReadModel;
import io.projeto.api.project.application.ProjectService;
import io.projeto.api.project.command.ProjectCreate;
import io.projeto.api.project.presentation.ProjectPresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectReadModel projectReadModel;

    @PostMapping
    @AuthRequired
    public APIResponse<ProjectPresentation> createProject(ProjetoAuthentication authentication, @RequestBody ProjectCreate request) {
        projectService.createProject(authentication, request);

        return APIResponse.of(projectReadModel.findByProjectId(request.getId()));
    }

    @GetMapping("/{projectId}")
    public APIResponse<ProjectPresentation> findByProjectId(@PathVariable("projectId") String projectId) {
        return APIResponse.of(projectReadModel.findByProjectId(projectId));
    }

}
