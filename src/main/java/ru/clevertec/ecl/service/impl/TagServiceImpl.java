package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.repository.TagRepository;
import ru.clevertec.ecl.service.TagService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final Mapper mapper;
    private final TagRepository repository;

    @Autowired
    public TagServiceImpl(Mapper mapper, TagRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public List<TagDto> findAll() {
        List<Tag> all = repository.findAll();
        all.forEach(tag -> tag.setCertificateList(new ArrayList<>()));
        return all.stream().map(mapper::convert).toList();

    }

    @Override
    public TagDto findByName(String name) {
        Tag byName = repository.findByName(name);
        byName.setCertificateList(new ArrayList<>());
        return mapper.convert(byName);
    }

    @Override
    public TagDto findById(int id) {
        Tag byId = repository.findById(id).orElse(null);
        if (byId != null) {
            byId.setCertificateList(new ArrayList<>());
        }
        return mapper.convert(byId);
    }

    @Override
    public TagDto save(TagDto tagDto) {
        Tag saved = repository.save(mapper.convert(tagDto));
        saved.setCertificateList(new ArrayList<>());
        return mapper.convert(saved);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
