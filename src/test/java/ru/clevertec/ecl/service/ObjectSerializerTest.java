package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.test_builder.TestBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectSerializerTest {

    private final ObjectSerializer serializer = new ObjectSerializer();

    @Test
    void objectToJson() {
        GiftCertificateDto testObject = TestBuilder.giftCertificateDtoBuilder().build();
        String expectedJson = "{\"id\":null,\"name\":\"test name\",\"description\":\"test description\",\"price\":1.11,\"duration\":432000.000000000,\"createDate\":\"2021-05-04T03:02:00\",\"lastUpdateDate\":\"2022-05-04T03:02:00\",\"tagList\":null}";
        String actualJson = serializer.objectToJson(testObject);
        assertThat(actualJson).isEqualTo(expectedJson);
    }


    @Test
    void getObject() {
        GiftCertificateDto testObject = TestBuilder.giftCertificateDtoBuilder().build();
        String testJson = "{\"id\":null,\"name\":\"test name\",\"description\":\"test description\",\"price\":1.11,\"duration\":432000.000000000,\"createDate\":\"2021-05-04T03:02:00\",\"lastUpdateDate\":\"2022-05-04T03:02:00\",\"tagList\":null}";
        GiftCertificateDto actualObject = serializer.getObject(testJson);
        assertThat(actualObject).isEqualTo(testObject);
    }
}