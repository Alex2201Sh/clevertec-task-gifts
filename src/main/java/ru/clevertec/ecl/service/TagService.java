package ru.clevertec.ecl.service;

import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.exceptions.MyException;

import java.util.List;

public interface TagService {
    List<TagDto> findAll();

    TagDto findByName(String name);

    TagDto findById(int id);

    TagDto save(TagDto tagDto);

    void delete(int id);
}
