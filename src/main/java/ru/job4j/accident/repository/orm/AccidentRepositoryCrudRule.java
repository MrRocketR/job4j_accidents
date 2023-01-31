package ru.job4j.accident.repository.orm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;
@Repository
public interface AccidentRepositoryCrudRule extends CrudRepository<Rule, Integer> {
    @Query("from Rule")
    List<Rule> getRules();
}
