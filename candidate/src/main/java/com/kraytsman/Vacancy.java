package com.kraytsman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vacancy {

    private Integer id;

    private String title;

    private String description;

//  the more - the better
    private Integer salaryAmount;

    private List<Candidate> candidates;

    public Vacancy() {
    }

    public Vacancy(Integer id, String title, String description, Integer salaryAmount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salaryAmount = salaryAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Integer getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Integer salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

}
