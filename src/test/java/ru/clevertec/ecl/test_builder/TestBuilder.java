package ru.clevertec.ecl.test_builder;

import org.postgresql.util.PGInterval;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;

import java.time.Duration;
import java.time.LocalDateTime;

public class TestBuilder {
    public static GiftCertificateDto.GiftCertificateDtoBuilder giftCertificateDtoBuilder() {
        return GiftCertificateDto.aCertificateDto()
                .setName("test name")
                .setDescription("test description")
                .setPrice(1.11F)
                .setDuration(Duration.ofDays(5))
                .setCreateDate(LocalDateTime.of(2021,5, 4,3,2))
                .setLastUpdateDate(LocalDateTime.of(2022,5, 4,3,2));
    }

    public static GiftCertificate.GiftCertificateBuilder giftCertificateBuilder() {
        return GiftCertificate.aCertificateDto()
                .setName("test name")
                .setDescription("test description")
                .setPrice(1.11F)
                .setDuration(Duration.ofDays(5))
                .setCreateDate(LocalDateTime.of(2021,5, 4,3,2))
                .setLastUpdateDate(LocalDateTime.of(2022,5, 4,3,2));
    }

}
