package com.kraytsman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@RestController
@RequestMapping("/vacancies")
public class VacancyController {

    private VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }


    @GetMapping
    public List<Vacancy> vacancies() {
        return this.vacancyService.getVacancies();
    }

    @GetMapping("/{vacancyId}")
    public Vacancy vacancies(@PathVariable Integer vacancyId) throws ServiceUnavailableException {
        return this.vacancyService.getVacancy(vacancyId);
    }

}
