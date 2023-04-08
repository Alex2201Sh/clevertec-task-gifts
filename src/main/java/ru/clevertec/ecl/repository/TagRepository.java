package ru.clevertec.ecl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.bean.Tag;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}
