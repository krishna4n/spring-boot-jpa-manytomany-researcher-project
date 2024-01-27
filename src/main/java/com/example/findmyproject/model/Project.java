package com.example.findmyproject.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int projectId;
    @Column(name = "name")
    private String projectName;
    @Column(name = "budget")
    private double budget;
    @ManyToMany(mappedBy = "projects")
    @JsonIgnoreProperties("projects")
    private List<Researcher> researchers = new ArrayList<>();

    public Project() {
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void setResearchers(List<Researcher> researchers) {
        this.researchers = researchers;
    }

    public List<Researcher> getResearchers() {
        return researchers;
    }

}