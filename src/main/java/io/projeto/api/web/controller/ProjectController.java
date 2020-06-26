package io.projeto.api.web.controller;

import io.projeto.api.domain.project.Project;
import io.projeto.api.web.request.ProjectCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {

    @PostMapping
    public Project createProject(@RequestBody ProjectCreateRequest request) {
        return null;
    }

}
