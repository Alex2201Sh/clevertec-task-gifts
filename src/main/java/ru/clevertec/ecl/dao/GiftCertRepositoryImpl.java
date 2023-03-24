package ru.clevertec.ecl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;

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
    public GiftCertificate findById(int id) {
        final String query = "SELECT * FROM gift_certificates WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        if (giftCertificate.getId() == 0) {
            jdbcTemplate.update("insert into gift_certificates values (?, ?, ?, ?, ?, ?)",
                    giftCertificate.getName(),
                    giftCertificate.getDescription(),
                    giftCertificate.getPrice(),
                    giftCertificate.getDuration(),
                    giftCertificate.getCreateDate(),
                    giftCertificate.getLastUpdateDate());
        } else {
            jdbcTemplate.update("UPDATE gift_certificates SET name = ?1, description = ?2," +
                            "price = ?3, duration = ?4," +
                            "create_date = ?5, last_update_date = ?6 " +
                            "WHERE id = ?7",
                    giftCertificate.getName(),
                    giftCertificate.getDescription(),
                    giftCertificate.getPrice(),
                    giftCertificate.getDuration(),
                    giftCertificate.getCreateDate(),
                    giftCertificate.getLastUpdateDate(),
                    giftCertificate.getId());
        }
        return findById(giftCertificate.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM gift_certificates WHERE id = ?", id);
    }
}
