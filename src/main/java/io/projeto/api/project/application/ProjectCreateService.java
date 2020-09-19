package io.projeto.api.project.application;

import io.projeto.api.common.security.ProjetoAuthentication;
import io.projeto.api.project.command.ProjectCreate;


public interface ProjectCreateService {
    void createProject(ProjetoAuthentication authentication, ProjectCreate request);

}
