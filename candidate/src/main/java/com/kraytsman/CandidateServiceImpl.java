package com.kraytsman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private List<Candidate> candidates;

    private RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;

    @Autowired
    public CandidateServiceImpl(List<Candidate> candidates, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.candidates = candidates;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List<Candidate> getCandidates() {
        return this.candidates;
    }

    @Override
    public Candidate getCandidate(Integer candidateId) {
        return candidates.stream().filter(c -> c.getId().equals(candidateId)).findFirst().orElseThrow(() -> new RuntimeException("Candidate was not found"));
    }

    @Override
    public List<Candidate> getCandidateByVacancyId(Integer vacancyId) {
        return candidates.stream().filter(c -> c.getVacancies() != null && c.getVacancies().contains(vacancyId)).collect(Collectors.toList());
    }

    @Override
    public Candidate setVacanciesToCandidate(Integer candidateId, List<Integer> vacanciesIds) throws ServiceUnavailableException {
        URI service = serviceUrl()
                .map(s -> s.resolve("/vacancies"))
                .orElseThrow(ServiceUnavailableException::new);
        Candidate candidate = getCandidate(candidateId);
        List<Vacancy> vacancies = restTemplate.exchange(service, HttpMethod.GET, null, new ParameterizedTypeReference<List<Vacancy>>() {}).getBody();
        Set<Integer> trueVacancies = Objects.requireNonNull(vacancies).stream().filter(v -> vacanciesIds.contains(v.getId())).map(Vacancy::getId).collect(Collectors.toSet());
        candidate.setVacancies(trueVacancies);
        candidates.remove(candidate);
        candidates.add(candidate);
        return candidate;
    }

    private Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("vacancy")
                .stream()
                .map(ServiceInstance::getUri)
          .findFirst();
    }

}
