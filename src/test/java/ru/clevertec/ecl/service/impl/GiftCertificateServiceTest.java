package ru.clevertec.ecl.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dao.hibernate.GiftCertRepositoryHibernateImpl;
import ru.clevertec.ecl.dao.impl.GiftCertRepositoryImpl;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.mapper.GiftCertificateMapperImpl;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.mapper.TagMapperImpl;
import ru.clevertec.ecl.service.GiftCertificatesService;
import ru.clevertec.ecl.test_builder.TestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GiftCertificateServiceTest {

    private static GiftCertificatesService service;
    private static Integer testObjectId;

    private static GiftCertificateDto testObject;

    @BeforeAll
    static void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/clevertec");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        JdbcTemplate template = new JdbcTemplate(dataSource);

//        GiftCertRepository repository = new GiftCertRepositoryImpl(template);
        GiftCertRepository repository = new GiftCertRepositoryHibernateImpl();

        service = new GiftCertificateServiceImpl(
                new Mapper(new TagMapperImpl(), new GiftCertificateMapperImpl()),
                repository);
    }

    @BeforeEach
    void init() {
        testObject = service.save(TestBuilder.giftCertificateDtoBuilder()
                .setName("testObject")
                .build());
        testObjectId = testObject.getId();
    }

    @AfterEach
    void cleanUp() {
        if (testObjectId != null) {
            service.delete(testObjectId);
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
    @Disabled()
    void findCertificateByTagName() {
        List<GiftCertificateDto> list = service.findCertificateByTagName("two");
        assertThat(list).isNotEmpty();
    }

    @Test
    void findById() throws MyException {
        GiftCertificateDto byId = service.findById(testObjectId);
        assertThat(byId).isEqualTo(testObject);
    }
}