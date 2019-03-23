package com.kraytsman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<Candidate> candidates() {
        return this.candidateService.getCandidates();
    }

    @GetMapping("/{candidateId}")
    public Candidate candidate(@PathVariable Integer candidateId) {
        return this.candidateService.getCandidate(candidateId);
    }
    @GetMapping("/v2/{vacancyId}")
    public List<Candidate> candidatesByVacancy(@PathVariable Integer vacancyId) {
        return this.candidateService.getCandidateByVacancyId(vacancyId);
    }

    @PostMapping("/{candidateId}/vacancies")
    public Candidate setVacanciesToCandidate(
            @PathVariable Integer candidateId,
            @RequestBody List<Integer> vacanciesIds) throws ServiceUnavailableException {
        return this.candidateService.setVacanciesToCandidate(candidateId, vacanciesIds);
    }

}
