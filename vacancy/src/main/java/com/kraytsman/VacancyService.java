package com.kraytsman;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public interface VacancyService {

    List<Vacancy> getVacancies();

    Vacancy getVacancy(Integer vacancyId) throws ServiceUnavailableException;

    Vacancy addVacancy(Vacancy vacancy);

}
