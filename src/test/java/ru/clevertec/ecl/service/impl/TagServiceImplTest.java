package ru.clevertec.ecl.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TagServiceImplTest {
    private final TagService service;
    private TagDto testObject;

    @Autowired
    TagServiceImplTest(TagService service) {
        this.service = service;
    }

    @BeforeEach
    void init() {
        TagDto tagDto = new TagDto();
        tagDto.setName("test name");
        testObject = service.save(tagDto);
    }

    @AfterEach
    void cleanUp() {
        if (testObject != null) {
            service.delete(testObject.getId());
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
        TagDto byId = service.findById(testObject.getId());
        assertThat(byId).isEqualTo(testObject);
    }
}