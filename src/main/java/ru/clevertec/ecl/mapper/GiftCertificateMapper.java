package ru.clevertec.ecl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.ecl.bean.GiftCertificate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

        @Override
        public GiftCertificate mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final GiftCertificate giftCertificate = new GiftCertificate();

            giftCertificate.setId(rs.getInt("id"));
            giftCertificate.setDescription(rs.getString("description"));
            giftCertificate.setPrice(rs.getFloat("price"));
            giftCertificate.setDuration(rs.getInt("duration"));
            giftCertificate.setCreateDate(rs.getTimestamp("creation_date").toLocalDateTime());
            giftCertificate.setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime());

            return giftCertificate;
    }
}
