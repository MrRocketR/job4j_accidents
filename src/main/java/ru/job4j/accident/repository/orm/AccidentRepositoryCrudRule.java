package ru.job4j.accident.repository.orm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.job4j.accident.model.Rule;

import java.util.List;

public interface AccidentRepositoryCrudRule extends CrudRepository<Rule, Integer> {
    @Query("from Rule")
    List<Rule> getRules();
}