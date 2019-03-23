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
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {

    private List<Vacancy> vacancies;

    private RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;

    @Autowired
    public VacancyServiceImpl(List<Vacancy> vacancies, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.vacancies = vacancies;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List<Vacancy> getVacancies() {
        return this.vacancies;
    }

    @Override
    public Vacancy getVacancy(Integer vacancyId) throws ServiceUnavailableException {
        URI service = serviceUrl()
                .map(s -> s.resolve("/candidates/v2/"+vacancyId))
                .orElseThrow(ServiceUnavailableException::new);
        Vacancy vacancy = this.vacancies.stream().filter(v -> v.getId().equals(vacancyId)).findFirst().orElseThrow(() -> new RuntimeException("Vacancy was not found"));
        List<Candidate> candidates = restTemplate.exchange(service, HttpMethod.GET, null, new ParameterizedTypeReference<List<Candidate>>() {}).getBody();
        vacancy.setCandidates(candidates);
        return vacancy;
    }

    @Override
    public Vacancy addVacancy(Vacancy vacancy) {
        return null;
    }

    private Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("candidate")
                .stream()
                .map(ServiceInstance::getUri)
                .findFirst();
    }

}
