package ru.clevertec.ecl.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.clevertec.ecl.dao.TagRepository;
import ru.clevertec.ecl.dao.impl.TagRepositoryImpl;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.mapper.GiftCertificateMapperImpl;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.mapper.TagMapperImpl;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TagServiceImplTest {

    private static TagService service;

    private static Integer testObjectId;

    private static TagDto testObject;

    @BeforeAll
    static void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/clevertec");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        JdbcTemplate template = new JdbcTemplate(dataSource);

        TagRepository repository = new TagRepositoryImpl(template);

        service = new TagServiceImpl(
                new Mapper(new TagMapperImpl(), new GiftCertificateMapperImpl()),
                repository);
    }

    @BeforeEach
    void init() {
        TagDto tagDto = new TagDto();
        tagDto.setName("test name");
        testObject = service.save(tagDto);
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
        List<TagDto> all = service.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void findByName() {
        TagDto tagDto = service.findByName(testObject.getName());
        assertThat(tagDto).isEqualTo(testObject);
    }

    @Test
    void findById() throws MyException {
        TagDto byId = service.findById(testObjectId);
        assertThat(byId).isEqualTo(testObject);
    }
}