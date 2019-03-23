package com.kraytsman;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public interface CandidateService {

    List<Candidate> getCandidates();

    Candidate getCandidate(Integer candidateId);

    List<Candidate> getCandidateByVacancyId(Integer vacancyId);

    Candidate setVacanciesToCandidate(Integer candidateId, List<Integer> vacanciesIds) throws ServiceUnavailableException;

}
