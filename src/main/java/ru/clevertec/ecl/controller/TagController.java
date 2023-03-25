package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dao.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagRepository repository;

    @Autowired
    public TagController(TagRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<Tag> gelAllTags() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id") int id) {
        return repository.findById(id);
    }

    @PostMapping()
    public Tag createGiftCertificate(@RequestBody Tag tag) {
        return repository.save(tag);
    }

    @PatchMapping("/{id}")
    public Tag update(@PathVariable("id") int id,
                      @RequestBody Tag tag) {
        Tag tagFromDb = repository.findById(id);
        tagFromDb.setName(tag.getName());
        return repository.save(tagFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
    }
}
