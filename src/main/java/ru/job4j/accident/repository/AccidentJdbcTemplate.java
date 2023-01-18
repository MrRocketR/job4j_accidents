package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;


    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    private final RowMapper<Rule> ruleRowMapper = (
            (rs, i) -> {
                Rule rule = new Rule();
                rule.setId(rs.getInt("id"));
                rule.setName(rs.getString("name"));
                return rule;
            });

    private final RowMapper<AccidentType> typeRowMapper = (
            (rs, i) -> {
                AccidentType type = new AccidentType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
               return type;
            });

    private final RowMapper<Accident> accidentRowMapper = (
            (rs, i) -> {
                Accident accident = new Accident();
                accident.setId(rs.getInt("id"));
                accident.setName(rs.getString("name"));
                accident.setText(rs.getString("text"));
                accident.setAddress(rs.getString("address"));
                AccidentType type = new AccidentType();
                type.setId(rs.getInt("type_id"));
                type.setName(rs.getString("type_name"));
                accident.setType(type);
                return accident;
            });

    private final ResultSetExtractor<Map<Integer, Accident>> extractorForOne = (rs) -> {
        Map<Integer, Accident> result = new HashMap<>();
        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("type_id"));
            type.setName(rs.getString("type_name"));
            accident.setType(type);
            result.putIfAbsent(accident.getId(), accident);
            result.get(accident.getId())
                    .getRules()
                    .add(new Rule(rs.getInt("rule_id"),
                            rs.getString("rule_name")));
        }
        return result;
    };


    private final ResultSetExtractor<Map<Integer, Accident>> extractor = (rs) -> {
        Map<Integer, Accident> result = new HashMap<>();
        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("type_id"));
            type.setName(rs.getString("type_name"));
            accident.setType(type);
            result.putIfAbsent(accident.getId(), accident);
            result.get(accident.getId())
                    .getRules()
                    .add(new Rule(rs.getInt("rule_id"),
                            rs.getString("rule_name")));
        }
        return result;
    };

    public List<AccidentType> getTypesFromDB() {
        String sql = "select * from accident_type";
        return jdbc.query(sql, typeRowMapper);
    }

    public List<Rule> getRulesFromDB() {
        String sql = "select * from rules";
        return jdbc.query(sql, ruleRowMapper);
    }

    public Accident findById(int id) {
        String sql = "select a.id, a.name, a.text, a.address, a.type_id, at.name as type_name,\n"
                + "ra.fk_rules_id as rule_id, r.name as rule_name\n"
                + "from accidents as a\n"
                + "inner join accident_type as at on a.type_id = at.id\n"
                + "inner join rules_accidents as ra on a.id = ra.fk_accident_id\n"
                + "inner join rules as r on r.id = ra.fk_rules_id\n"
                + "where a.id = ?";
        Map<Integer, Accident> accidentById = jdbc.query(sql, extractorForOne, id);
        return accidentById.get(id);
    }

    public Collection<Accident> show() {
        String sql = "select a.id, a.name, a.text, a.address, a.type_id, at.name as type_name,\n"
                + "ra.fk_rules_id as rule_id, r.name as rule_name\n"
                + "from accidents as a\n"
                + "inner join accident_type as at on a.type_id = at.id\n"
                + "inner join rules_accidents as ra on a.id = ra.fk_accident_id\n"
                + "inner join rules as r on r.id = ra.fk_rules_id";
        Map<Integer, Accident> accidentMap = jdbc.query(sql, extractor);
        return accidentMap.values();
    }

    public void insertNewAccident(Accident accident, String[] ids) {
        String query = "insert into accidents (name, text, address, type_id) values (?,?,?,?)";
        String updateRules = "insert into rules_accidents (fk_accident_id, fk_rules_id) values (?,?)";
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, holder);

        Integer idS = (Integer) holder.getKeys().get("id");
        for (String id : ids) {
            jdbc.update(
                    updateRules,
                    idS,
                    Integer.parseInt(id)
            );
        }
    }


    public void update(Accident accident) {
        String sql = "insert into accidents (name, text, address) values (?,?,?) where id = ?";
        jdbc.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getId());
            return statement;
        });
            }

    }

