package ru.job4j.accident.repository.hiber;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
@AllArgsConstructor
public class RulesHibernate {


    private final CrudHibernate repo;

    public List<Rule> getRules() {
        String sql = "From Rule";
        return repo.query(sql, Rule.class);
    }
}
