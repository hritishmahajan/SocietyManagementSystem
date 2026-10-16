package com.Hriitsh.societyvms.society_visitor_management.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.Hriitsh.societyvms.society_visitor_management.entity.Resident;

@Repository
public class ResidentJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Maps one row of the "residents" table into a Resident object
    private Resident mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Resident resident = new Resident();
        resident.setId(rs.getLong("id"));
        resident.setName(rs.getString("name"));
        resident.setFlatNumber(rs.getString("flat_number"));
        resident.setPhoneNumber(rs.getString("phone_number"));
        resident.setEmail(rs.getString("email"));
        return resident;
    }

    public Resident save(Resident resident) {
        String sql = "INSERT INTO residents (name, flat_number, phone_number, email) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, resident.getName());
            ps.setString(2, resident.getFlatNumber());
            ps.setString(3, resident.getPhoneNumber());
            ps.setString(4, resident.getEmail());
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        resident.setId(generatedId);
        return resident;
    }

    public List<Resident> findAll() {
        String sql = "SELECT id, name, flat_number, phone_number, email FROM residents";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Optional<Resident> findById(Long id) {
        String sql = "SELECT id, name, flat_number, phone_number, email FROM residents WHERE id = ?";
        try {
            Resident resident = jdbcTemplate.queryForObject(sql, this::mapRow, id);
            return Optional.ofNullable(resident);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Resident update(Resident resident) {
        String sql = "UPDATE residents SET name = ?, flat_number = ?, phone_number = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                resident.getName(),
                resident.getFlatNumber(),
                resident.getPhoneNumber(),
                resident.getEmail(),
                resident.getId());
        return resident;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM residents WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM residents WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
