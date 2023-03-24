package ru.clevertec.ecl.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.bean.GiftCertificate;

import java.sql.ResultSet;
import java.util.List;

@Component
public interface GiftCertRepository {
//    RowMapper<GiftCertificate> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
//        final GiftCertificate giftCertificate = new GiftCertificate();
//
//        giftCertificate.setId(rs.getInt("id"));
//        giftCertificate.setName(rs.getString("name"));
//        giftCertificate.setDescription(rs.getString("description"));
//        giftCertificate.setPrice(rs.getFloat("price"));
//        giftCertificate.setDuration(rs.getInt("duration"));
//        giftCertificate.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
//        giftCertificate.setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime());
//
//        return giftCertificate;
//    };

    List<GiftCertificate> findAll();

    GiftCertificate findById(int id);

    GiftCertificate save(GiftCertificate giftCertificate);

    int delete(int id);
}
