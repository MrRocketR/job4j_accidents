package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.job4j.accident.model.AccidentType;

import java.util.List;

public interface AccidentRepositoryCrudType extends CrudRepository<AccidentType, Integer> {
    @Query("from AccidentType")
    List<AccidentType> getTypes();
}

