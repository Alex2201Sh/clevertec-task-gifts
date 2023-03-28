package ru.clevertec.ecl.service;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;

import java.util.List;

public interface CrudService {
    List<GiftCertificateDto> findAll();

    List<GiftCertificateDto> findByPartOfName(String partOnName);

    List<GiftCertificateDto> findByPartOfDescription(String partOnName);

    List<GiftCertificateDto> findCertificateByTagName(String tagName);

    GiftCertificateDto findById(int id) throws MyException;

    GiftCertificateDto save(GiftCertificateDto giftCertificateDto);

    int delete(int id);
}
