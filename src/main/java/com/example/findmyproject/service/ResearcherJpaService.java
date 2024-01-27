package com.example.findmyproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ProjectJpaRepository;
import com.example.findmyproject.repository.ResearcherJpaRepository;
import com.example.findmyproject.repository.ResearcherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ResearcherJpaService implements ResearcherRepository {

    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Override
    public ArrayList<Researcher> getAllResearchers() {
        try {
            List<Researcher> researchers = researcherJpaRepository.findAll();
            return (ArrayList<Researcher>) researchers;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Researcher addResearch(Researcher research) {
        try {
            List<Integer> projectIds = new ArrayList<>();
            for (Project project : research.getProjects()) {
                projectIds.add(project.getProjectId());
            }
            List<Project> matchedProjects = projectJpaRepository.findAllById(projectIds);
            if (matchedProjects.size() != projectIds.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            research.setProjects(matchedProjects);
            Researcher savedResearcher = researcherJpaRepository.save(research);
            return savedResearcher;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Researcher getResearchById(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            return researcher;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Researcher updateReasearch(Researcher researcher, int researcherId) {
        try {
            Researcher existingResearcher = researcherJpaRepository.findById(researcherId).get();
            if (researcher.getResearcherName() != null) {
                existingResearcher.setResearcherName(researcher.getResearcherName());
            }
            if (researcher.getSpecialization() != null) {
                existingResearcher.setSpecialization(researcher.getSpecialization());
            }
            if (researcher.getProjects() != null) {
                List<Integer> projectIds = new ArrayList<>();
                for (Project project : researcher.getProjects()) {
                    projectIds.add(project.getProjectId());
                }

                List<Project> newProjects = projectJpaRepository.findAllById(projectIds);
                if (newProjects.size() != projectIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                existingResearcher.setProjects(newProjects);

            }
            Researcher updatedResearcher = researcherJpaRepository.save(existingResearcher);
            return updatedResearcher;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteResearch(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            List<Project> projects = researcher.getProjects();
            for (Project project : projects) {
                project.getResearchers().remove(researcher);
            }
            projectJpaRepository.saveAll(projects);
            researcherJpaRepository.deleteById(researcherId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public List<Project> getResearchProjects(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            List<Project> projects = researcher.getProjects();
            return projects;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}