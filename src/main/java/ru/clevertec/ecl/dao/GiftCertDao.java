package ru.clevertec.ecl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.bean.GiftCertificate;

@Repository
public class GiftCertDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    private SimpleJdbcInsert simpleJdbcInsert;
//
//    private SimpleJdbcCall simpleJdbcCall;


//    @Autowired
//    public void setDataSource(final DataSource dataSource) {
//
//        jdbcTemplate = new JdbcTemplate(dataSource);
////        jdbcTemplate = new JdbcTemplate(new SpringJdbcConfig().postgresDataSource());
//
//        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("gift_certificates");
//    }

    public GiftCertificate getById(final int id) {
//        setDataSource(new SpringJdbcConfig().postgresDataSource());
//        final String query = "SELECT * FROM gift_certificates WHERE id = ?";
        return jdbcTemplate.queryForObject("SELECT * FROM gift_certificates WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class));

    }
}
