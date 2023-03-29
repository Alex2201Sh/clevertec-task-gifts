package ru.clevertec.ecl.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.test_builder.TestBuilder;

import static org.assertj.core.api.Assertions.*;

class ObjectSerializerTest {

    private final ObjectSerializer serializer = new ObjectSerializer();

    @Test
    void objectToJson() {
        GiftCertificateDto testObject = TestBuilder.giftCertificateDtoBuilder().build();
        String expectedJson = "{\"id\":null,\"name\":\"test name\",\"description\":\"test description\",\"price\":1.11,\"duration\":{\"type\":\"interval\",\"value\":\"0 years 0 mons 0 days 0 hours 0 mins 0.0 secs\",\"years\":0,\"months\":0,\"days\":0,\"hours\":0,\"minutes\":0,\"wholeSeconds\":0,\"microSeconds\":0,\"seconds\":0.0,\"null\":false},\"createDate\":\"2021-05-04T03:02:00\",\"lastUpdateDate\":\"2022-05-04T03:02:00\"}";
        String actualJson = serializer.objectToJson(testObject);
        assertThat(actualJson).isEqualTo(expectedJson);
    }


    @Test
    void getObject() {
        GiftCertificateDto testObject = TestBuilder.giftCertificateDtoBuilder().build();
        String testJson = "{\"id\":null,\"name\":\"test name\",\"description\":\"test description\",\"price\":1.11,\"duration\":{\"type\":\"interval\",\"value\":\"0 years 0 mons 0 days 0 hours 0 mins 0.0 secs\",\"years\":0,\"months\":0,\"days\":0,\"hours\":0,\"minutes\":0,\"wholeSeconds\":0,\"microSeconds\":0,\"seconds\":0.0,\"null\":false},\"createDate\":\"2021-05-04T03:02:00\",\"lastUpdateDate\":\"2022-05-04T03:02:00\"}";
        GiftCertificateDto actualObject = serializer.getObject(testJson);
        assertThat(actualObject).isEqualTo(testObject);
    }
}