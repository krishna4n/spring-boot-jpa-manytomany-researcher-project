package com.example.findmyproject.repository;

import com.example.findmyproject.model.*;
import java.util.List;
import java.util.ArrayList;

public interface ResearcherRepository {
    ArrayList<Researcher> getAllResearchers();

    Researcher addResearch(Researcher research);

    Researcher getResearchById(int researchId);

    Researcher updateReasearch(Researcher research, int researchId);

    void deleteResearch(int researchId);

    List<Project> getResearchProjects(int researchId);
}