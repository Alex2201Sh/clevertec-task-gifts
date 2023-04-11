package ru.clevertec.ecl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.User;

import java.util.List;

@Repository
@Transactional
public interface GiftCertRepository extends JpaRepository<GiftCertificate, Integer> {

    List<GiftCertificate> findByNameContaining(String name);

    List<GiftCertificate> findByDescriptionContaining(String description);

    @Query(value = "SELECT t.certificateList FROM Tag t WHERE t.id = (SELECT t.id FROM Tag t WHERE t.name = ?1)")
    List<GiftCertificate> findGiftCertificatesByTagName(String tagName);

//    @Query(value = "select u.certificateList FROM User u WHERE u = ?1")
//    List<GiftCertificate> findGiftCertificatesByUser(User user);


}
