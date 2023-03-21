package ru.clevertec.ecl.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public GiftCertificate findOne(String id) {
        final String query = "SELECT * FROM gift_certificates WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
