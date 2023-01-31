package ru.job4j.accident.repository.orm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
@Repository
public interface AccidentRepositoryCrudType extends CrudRepository<AccidentType, Integer> {
    @Query("from AccidentType")
    List<AccidentType> getTypes();
}

