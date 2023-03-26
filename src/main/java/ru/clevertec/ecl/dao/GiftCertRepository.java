package ru.clevertec.ecl.dao;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.exceptions.MyException;

import java.util.List;

public interface GiftCertRepository {

    List<GiftCertificate> findAll();

    List<GiftCertificate> findByPartOfName(String partOnName);

    List<GiftCertificate> findByPartOfDescription(String partOnName);

    List<GiftCertificate> findCertificateByTagName(String tagName);

    GiftCertificate findById(int id) throws MyException;

    GiftCertificate save(GiftCertificate giftCertificate);

    int delete(int id);

}
