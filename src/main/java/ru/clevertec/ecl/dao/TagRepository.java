package ru.clevertec.ecl.dao;

import ru.clevertec.ecl.bean.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> findAll();

    Tag findById(int id);

    Tag findByName(String name);

    Tag save(Tag tag);

    int delete(int id);
}
