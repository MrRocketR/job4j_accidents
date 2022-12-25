package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

@Service
@AllArgsConstructor
public class AccidentServiceJDBC {
    private final AccidentJdbcTemplate accidentsRepostiory;

    public void create(Accident accident) {
        accidentsRepostiory.save(accident);
    }
}
