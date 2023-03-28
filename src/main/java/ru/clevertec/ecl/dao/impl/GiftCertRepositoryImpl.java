package ru.clevertec.ecl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.exceptions.MyException;

import java.util.List;

@Component
public class GiftCertRepositoryImpl implements GiftCertRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query("SELECT * FROM gift_certificates",
                new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public List<GiftCertificate> findByPartOfName(String partOfName) {
        final String query = "SELECT * FROM gift_certificates WHERE name LIKE concat('%',?,'%')";
        return jdbcTemplate.query(query, new Object[]{partOfName},
                new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        final String query = "SELECT * FROM gift_certificates WHERE description LIKE concat('%',?,'%')";
        return jdbcTemplate.query(query, new Object[]{partOfDescription},
                new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public List<GiftCertificate> findCertificateByTagName(String tagName) {
        final String query = "SELECT * from gift_certificates where id " +
                "IN (SELECT certificate_id FROM certificate_tag WHERE tag_id = " +
                "(SELECT tags.id FROM tags WHERE name = ?))";
        return jdbcTemplate.query(query, new Object[]{tagName},
                new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public GiftCertificate findById(int id) throws MyException {
        final String query = "SELECT * FROM gift_certificates WHERE id = ?";
        GiftCertificate certificate;
        try {
            certificate = jdbcTemplate.queryForObject(query, new Object[]{id},
                    new BeanPropertyRowMapper<>(GiftCertificate.class));
        } catch (EmptyResultDataAccessException e) {
            throw new MyException("Requested resource not found (id =  " + id + ")", "40401");
        }
        return certificate;
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        if (giftCertificate.getId() == null) {
            jdbcTemplate.update("insert into gift_certificates (name, description, price, duration, create_date, last_update_date) " +
                            "values (?, ?, ?, ?, ?, ?)",
                    giftCertificate.getName(),
                    giftCertificate.getDescription(),
                    giftCertificate.getPrice(),
                    giftCertificate.getDuration(),
                    giftCertificate.getCreateDate(),
                    giftCertificate.getLastUpdateDate());
        } else {
            jdbcTemplate.update("UPDATE gift_certificates SET name = ?, description = ?," +
                            "price = ?, duration = ?," +
                            "create_date = ?, last_update_date = ? " +
                            "WHERE id = ?",
                    giftCertificate.getName(),
                    giftCertificate.getDescription(),
                    giftCertificate.getPrice(),
                    giftCertificate.getDuration(),
                    giftCertificate.getCreateDate(),
                    giftCertificate.getLastUpdateDate(),
                    giftCertificate.getId());
        }
        return giftCertificate;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM gift_certificates WHERE id = ?", id);
    }
}
