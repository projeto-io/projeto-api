package io.projeto.api.project.application;

import io.projeto.api.project.command.ProjectCreate;


public interface ProjectCreateService {
    void createProject(ProjectCreate request);

}
