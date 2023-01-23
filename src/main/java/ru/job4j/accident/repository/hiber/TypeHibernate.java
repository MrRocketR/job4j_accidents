package ru.job4j.accident.repository.hiber;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeHibernate {

    private final CrudHibernate repo;

    public List<AccidentType> getTypes() {
        String sql = "From AccidentType";
        return repo.query(sql, AccidentType.class);
    }
}

