package ru.clevertec.ecl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dao.TagRepository;

import java.util.List;

@Component
public class TagRepositoryImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query("SELECT * FROM tags",
                new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag findById(int id) {
        final String query = "SELECT * FROM tags WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id},
                new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag findByName(String name) {
        final String query = "SELECT * FROM tags WHERE name = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{name},
                new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            jdbcTemplate.update("INSERT INTO tags (name) " +
                            "values (?)",
                    tag.getName());
            String findSavedObject = "SELECT id from tags WHERE name = ?";
            Integer savedObjectId = jdbcTemplate.queryForObject(findSavedObject,
                    new Object[]{tag.getName()}, Integer.class);
            tag.setId(savedObjectId);
        } else {
            jdbcTemplate.update("UPDATE tags SET name = ? WHERE id = ?",
                    tag.getName(), tag.getId());
        }
        return tag;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM tags WHERE id = ?", id);
    }
}
