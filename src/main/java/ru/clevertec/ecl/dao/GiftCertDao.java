package ru.clevertec.ecl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.config.SpringJdbcConfig;

import javax.sql.DataSource;

import static ru.clevertec.ecl.dao.GiftCertRepository.ROW_MAPPER;

@Repository
public class GiftCertDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(final DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate = new JdbcTemplate(new SpringJdbcConfig().postgresDataSource());

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("gift_certificates");
    }

    public GiftCertificate getById(final int id) {
        setDataSource(new SpringJdbcConfig().postgresDataSource());
        final String query = "SELECT * FROM gift_certificates WHERE id = ?";
//        return jdbcTemplate.queryForObject(query, new Object[] { id }, new GiftCertificateMapper());
        return jdbcTemplate.queryForObject("SELECT * FROM gift_certificates WHERE id = ?", new Object[]{id}, ROW_MAPPER);

    }
}
