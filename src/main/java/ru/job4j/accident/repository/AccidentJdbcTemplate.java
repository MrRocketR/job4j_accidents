package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private Map<Integer, Rule> rules;
    private Map<Integer, AccidentType> types;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        rules = getRulesForDB();
        types = getTypesForDB();
    }

    public Accident save(Accident accident, String[] ids) {
        String updateSQL = "insert into accident (name, text, address, type_id) values (?,?,?,?)";
        String updateManyToMany = "insert into rules_accidents (fk_accident_id, fk_rules_id) values (?,?)";
        jdbc.update(updateSQL, accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());

        for (String id : ids) {
            jdbc.update(updateManyToMany, accident.getId(), Integer.valueOf(id));
        }
        return accident;
    }

    public Map<Integer, Rule> getRulesForDB() {
        String sql = "select from rules";
        List<Rule> list = jdbc.query(
                sql,
                (rs, rowNum) ->
                        new Rule(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
        );
        for (Rule r : list) {
            rules.put(r.getId(), r);
        }
        return rules;
    }

    public Collection<Rule> getRulesList() {
        return rules.values();
    }

    public Map<Integer, AccidentType> getTypesForDB() {
        String sql = "select from accident_type";
        List<AccidentType> list = jdbc.query(
                sql,
                (rs, rowNum) ->
                        new AccidentType(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
        );
        for (AccidentType ac : list) {
            types.put(ac.getId(), ac);
        }
        return types;
    }

    public Collection<AccidentType> getTypesList() {
        return types.values();
    }

    public List<Accident> getAll() {
        String sql = "select id, name, text, address, type_id from accidents";
        return jdbc.query(sql,
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(types.get(rs.getInt("type_id")));
                    return accident;
                });
    }
}
