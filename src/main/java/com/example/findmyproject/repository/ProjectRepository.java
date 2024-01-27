package com.example.findmyproject.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;

public interface ProjectRepository {
    ArrayList<Project> getAllProjects();

    Project addProject(Project project);

    Project getProjectById(int projectId);

    Project updateProject(Project project, int projectId);

    void deleteProject(int projectId);

    List<Researcher> getProjectResearchers(int projectId);
}