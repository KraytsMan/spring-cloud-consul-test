package com.kraytsman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String phone;

    private String address;

    private String shortBio;

    @JsonProperty
    private Set<Integer> vacancies;

//  Recruiters do not read till here
    @JsonProperty
    private Set<Skill> skills = new HashSet<>();

    public Candidate() {
    }

    public Candidate(Integer id, String firstName, String lastName, Integer age, String phone, String address, String shortBio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.shortBio = shortBio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return firstName.equals(candidate.firstName) &&
                lastName.equals(candidate.lastName) &&
                age.equals(candidate.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Integer> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Integer> vacancies) {
        this.vacancies = vacancies;
    }
}
