package com.example.findmyproject.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@Entity
@Table(name = "researcher")
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int researcherId;
    @Column(name = "name")
    private String researcherName;
    @Column(name = "specialization")
    private String specialization;

    @ManyToMany
    @JoinTable(name = "researcher_project", joinColumns = @JoinColumn(name = "researcherid"), inverseJoinColumns = @JoinColumn(name = "projectid"))
    @JsonIgnoreProperties("researchers")
    private List<Project> projects = new ArrayList<>();

    public Researcher() {
    }

    public void setResearcherId(int researcherId) {
        this.researcherId = researcherId;
    }

    public int getResearcherId() {
        return researcherId;
    }

    public void setResearcherName(String researcherName) {
        this.researcherName = researcherName;
    }

    public String getResearcherName() {
        return researcherName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }

}