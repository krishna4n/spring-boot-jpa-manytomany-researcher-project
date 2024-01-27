package com.example.findmyproject.controller;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.service.ResearcherJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ResearcherController {

    @Autowired
    private ResearcherJpaService researcherJpaService;

    @GetMapping("/researchers")
    public ArrayList<Researcher> getAllResearchers() {
        return researcherJpaService.getAllResearchers();
    }

    @PostMapping("/researchers")
    public Researcher addResearcher(@RequestBody Researcher researcher) {
        return researcherJpaService.addResearch(researcher);
    }

    @GetMapping("/researchers/{researcherId}")
    public Researcher getResearcherByID(@PathVariable("researcherId") int researcherId) {
        return researcherJpaService.getResearchById(researcherId);
    }

    @PutMapping("/researchers/{researcherId}")
    public Researcher updateResearcher(@PathVariable("researcherId") int researcherId,
            @RequestBody Researcher researcher) {
        return researcherJpaService.updateReasearch(researcher, researcherId);
    }

    @DeleteMapping("/researchers/{researcherId}")
    public void deleteResearcher(@PathVariable("researcherId") int researcherId) {
        researcherJpaService.deleteResearch(researcherId);
    }

    @GetMapping("/researchers/{researcherId}/projects")
    public List<Project> getResearcheProjects(@PathVariable("researcherId") int researcherId) {
        return researcherJpaService.getResearchProjects(researcherId);
    }

}