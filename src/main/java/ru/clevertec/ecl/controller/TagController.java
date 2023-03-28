package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService service;
    private final Mapper mapper;

    @Autowired
    public TagController(TagService service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<TagDto> gelAllTags() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable("id") int id) throws MyException {
        return service.findById(id);
    }

    @PostMapping()
    public TagDto createGiftCertificate(@RequestBody Tag tag) {
        return service.save(mapper.convert(tag));
    }

    @PatchMapping("/{id}")
    public TagDto update(@PathVariable("id") int id,
                         @RequestBody Tag tag) throws MyException {
        TagDto tagFromDb = service.findById(id);
        tagFromDb.setName(tag.getName());
        return service.save(tagFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
