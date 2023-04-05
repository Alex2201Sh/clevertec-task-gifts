package ru.clevertec.ecl.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.service.GiftCertificatesService;
import ru.clevertec.ecl.test_builder.TestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GiftCertificateServiceTest {

    private final GiftCertificatesService service;
    private GiftCertificateDto testObject;

    @Autowired
    GiftCertificateServiceTest(GiftCertificatesService service) {
        this.service = service;
    }

    @BeforeEach
    void init() {
        testObject = service.save(TestBuilder.giftCertificateDtoBuilder()
                .setName("testObject")
                .build());
    }

    @AfterEach
    void cleanUp() {
        if (testObject != null) {
            service.delete(testObject.getId());
        }
    }

    @Test
    void findAll() {
        List<GiftCertificateDto> all = service.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void findByPartOfName() {
        List<GiftCertificateDto> list = service.findByPartOfName("test");
        assertThat(list).isNotEmpty();
    }

    @Test
    void findByPartOfDescription() {
        List<GiftCertificateDto> list = service.findByPartOfDescription("test");
        assertThat(list).isNotEmpty();
    }

    @Test
    void findCertificateByTagName() {
        List<GiftCertificateDto> list = service.findCertificateByTagName("one");
        assertThat(list).isNotEmpty();
    }

    @Test
    void findById() throws MyException {
        GiftCertificateDto byId = service.findById(testObject.getId());
        assertThat(byId).isEqualTo(testObject);
    }
}