package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcTodoRepository implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("todo")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", todo.getUsername());
        parameters.put("contents", todo.getContents());
        parameters.put("password", todo.getPassword());
        parameters.put("date", todo.getDate());

        Number key = jdbcInsert.executeAndReturnKey(parameters);

        return new TodoResponseDto(key.longValue(), todo.getUsername(), todo.getContents(), todo.getDate());
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        String sql = "SELECT * FROM todo";
        return jdbcTemplate.query(sql, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("contents"),
                        rs.getTimestamp("date").toLocalDateTime()
                );
            }
        });
    }

    @Override
    public Optional<TodoResponseDto> findTodoById(Long id) {
        String sql = "SELECT * FROM todo WHERE id = ?";
        return jdbcTemplate.query(sql, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("contents"),
                        rs.getTimestamp("date").toLocalDateTime()
                );
            }
        }, id).stream().findAny();
    }


    @Override
    public int updateContents(Long id, String username, String contents, LocalDateTime date, String password) {
        String checkSql = "SELECT COUNT(*) FROM todo WHERE id = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id, password);

        if (count == 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect password.");
        }

        String sql = "UPDATE todo SET username = ?, contents = ?, date = ? WHERE id = ?";
        return jdbcTemplate.update(sql, username, contents, date, id);
    }

    @Override
    public int deleteTodo(Long id, String password) {
        String checkSql = "SELECT COUNT(*) FROM todo WHERE id = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id, password);

        if (count == 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect password.");
        }

        String sql = "DELETE FROM todo WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
