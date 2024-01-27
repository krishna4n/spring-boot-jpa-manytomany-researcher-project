package com.example.findmyproject.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.service.ProjectJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    @Autowired
    private ProjectJpaService projectJpaService;

    @GetMapping("/researchers/projects")
    public ArrayList<Project> getAllProjects() {
        return projectJpaService.getAllProjects();
    }

    @PostMapping("/researchers/projects")
    public Project addProject(@RequestBody Project project) {
        return projectJpaService.addProject(project);
    }

    @GetMapping("/researchers/projects/{projectId}")
    public Project getProjectById(@PathVariable("projectId") int projectId) {
        return projectJpaService.getProjectById(projectId);
    }

    @PutMapping("/researchers/projects/{projectId}")
    public Project updateProject(@RequestBody Project project, @PathVariable("projectId") int projectId) {
        return projectJpaService.updateProject(project, projectId);
    }

    @DeleteMapping("/researchers/projects/{projectId}")
    public void deleteProject(@PathVariable("projectId") int projectId) {
        projectJpaService.deleteProject(projectId);
    }

    @GetMapping("/projects/{projectId}/researchers")
    public List<Researcher> getProjectResearchers(@PathVariable("projectId") int projectId) {
        return projectJpaService.getProjectResearchers(projectId);
    }

}