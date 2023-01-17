package ru.job4j.accident.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentMapper implements RowMapper {
    @Override
    public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
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
    }



}
