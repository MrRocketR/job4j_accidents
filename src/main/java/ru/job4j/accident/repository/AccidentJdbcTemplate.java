package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private Map<Integer, Rule> rules = new HashMap<>();
    private Map<Integer, AccidentType> types = new HashMap<>();

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        rules = getRulesForDB();
        types = getTypesForDB();
    }

    private Map<Integer, Rule> getRulesForDB() {
        String sql = "select * from rules";
        List<Rule> listRs = jdbc.query(
                sql, (rs, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
        for (Rule r : listRs) {
            rules.put(r.getId(), r);
        }
        return rules;
    }

    private Map<Integer, AccidentType> getTypesForDB() {
        String sql = "select * from accident_type";
        List<AccidentType> listRs = jdbc.query(
                sql, (rs, rowNum) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
        for (AccidentType type : listRs) {
            types.put(type.getId(), type);
        }
        return types;
    }

    public Collection<Accident> show() {
        String sql = "select a.id, a.name, a.text, a.address, a.type_id, at.name as type_name\n"
                + "from accidents as a\n"
                + "inner join accident_type as at\n"
                + "on at.id = a.type_id";
        List<Accident> accidents = jdbc.query(sql, new AccidentMapper());
        return accidents;
    }


    public void insert(Accident accident, String[] ids) {
        String updateAccident = "insert into accidents (id, name, text, address, type_id) values (?,?,?,?,?)";
        String updateRules = "insert into rules_accidents (fk_accident_id, fk_rules_id) values (?,?)";
        jdbc.update(updateAccident,
                accident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        for (String id : ids) {
            jdbc.update(updateRules, accident.getId(), Integer.valueOf(id));
        }
    }

    public void update(Accident accident, int id) {
        String sql = "insert into accidents (name, text, address) values (?,?,?) where id = ?";
        jdbc.update(sql,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId());
    }
    public Accident findById(int id) {
        String sql = "select * from accidents where id = ?";
        Accident accident = (Accident) jdbc.query(sql, new AccidentMapper());
        return accident;
    }
    }

