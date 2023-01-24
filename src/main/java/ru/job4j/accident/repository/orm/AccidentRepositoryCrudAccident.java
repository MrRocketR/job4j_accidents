package ru.job4j.accident.repository.orm;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface AccidentRepositoryCrudAccident extends CrudRepository<Accident, Integer> {
    @Query("from Accident a join fetch a.type")
    List<Accident> findAllAccidents();

    @Modifying
    @Query("update Accident as a set a.name = ?1, a.text = ?2, a.address = ?3 where a.id = ?4")
    void updateAccident(String name, String text, String address, int id);

    @Query("from Accident a join fetch a.type where a.id = ?1")
    Accident getById(int id);


}

