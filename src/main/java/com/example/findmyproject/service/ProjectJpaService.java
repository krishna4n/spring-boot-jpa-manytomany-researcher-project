package com.example.findmyproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ProjectJpaRepository;
import com.example.findmyproject.repository.ProjectRepository;
import com.example.findmyproject.repository.ResearcherJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectJpaService implements ProjectRepository {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Override
    public ArrayList<Project> getAllProjects() {
        try {
            List<Project> projects = projectJpaRepository.findAll();
            return (ArrayList<Project>) projects;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project addProject(Project project) {
        try {
            List<Integer> researcherIds = new ArrayList<>();
            for (Researcher researcher : project.getResearchers()) {
                researcherIds.add(researcher.getResearcherId());
            }
            List<Researcher> researchers = researcherJpaRepository.findAllById(researcherIds);
            if (researchers.size() != researcherIds.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            for (Researcher Researcher : researchers) {
                Researcher.getProjects().add(project);
            }
            project.setResearchers(researchers);
            Project savedProject = projectJpaRepository.save(project);
            researcherJpaRepository.saveAll(researchers);
            return savedProject;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project getProjectById(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project updateProject(Project project, int projectId) {
        try {
            Project existingProject = projectJpaRepository.findById(projectId).get();
            if (project.getProjectName() != null) {
                existingProject.setProjectName(project.getProjectName());
            }
            if (project.getBudget() != 0.0) {
                existingProject.setBudget(project.getBudget());
            }
            if (project.getResearchers() != null) {
                List<Researcher> existingResearchers = existingProject.getResearchers();
                for (Researcher researcher : existingResearchers) {
                    researcher.getProjects().remove(existingProject);
                }
                researcherJpaRepository.saveAll(existingResearchers);
                List<Integer> researcherIds = new ArrayList<>();
                for (Researcher researcher : project.getResearchers()) {
                    researcherIds.add(researcher.getResearcherId());
                }
                List<Researcher> newResearchers = researcherJpaRepository.findAllById(researcherIds);
                if (researcherIds.size() != newResearchers.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                for (Researcher researcher : newResearchers) {
                    researcher.getProjects().add(existingProject);
                }
                researcherJpaRepository.saveAll(newResearchers);
                existingProject.setResearchers(newResearchers);

            }
            Project updatedProject = projectJpaRepository.save(existingProject);
            return updatedProject;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProject(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            List<Researcher> researchers = project.getResearchers();
            for (Researcher research : researchers) {
                research.getProjects().remove(project);
            }
            researcherJpaRepository.saveAll(researchers);
            projectJpaRepository.deleteById(projectId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Researcher> getProjectResearchers(int projectId) {
        try {

            Project project = projectJpaRepository.findById(projectId).get();
            return project.getResearchers();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}