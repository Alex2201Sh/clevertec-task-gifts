package ru.clevertec.ecl.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.ecl.test_builder.TestBuilder.giftCertificateBuilder;
import static ru.clevertec.ecl.utils.CustomBeanUtils.copyProperties;

class CustomBeanUtilsTest {

    private GiftCertificate builded;
    private GiftCertificateDto buildedDto;

    @BeforeEach
    void init() {
        builded = giftCertificateBuilder().setName("testName")
                .setDescription("testDescription").build();
        buildedDto = new GiftCertificateDto();
    }

    @Test
    void copyPropertiesCheckName() {
        copyProperties(builded, buildedDto);
        assertThat(buildedDto.getName()).isEqualTo(builded.getName());
    }

    @Test
    void copyPropertiesNullFieldCheck() {
        Float testValue = 5.55F;
        builded.setPrice(null);
        buildedDto.setPrice(testValue);
        copyProperties(builded, buildedDto);
        assertThat(buildedDto.getPrice()).isEqualTo(testValue);
    }
}