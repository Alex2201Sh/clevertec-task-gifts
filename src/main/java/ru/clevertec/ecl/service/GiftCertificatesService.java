package ru.clevertec.ecl.service;

import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;

import java.util.List;

public interface GiftCertificatesService {
    List<GiftCertificateDto> findAll();

    List<GiftCertificateDto> findByPartOfName(String partOnName);

    List<GiftCertificateDto> findByPartOfDescription(String partOnName);

    List<GiftCertificateDto> findCertificateByTagName(String tagName);

    GiftCertificateDto findById(int id) throws MyException;

    GiftCertificateDto save(GiftCertificateDto giftCertificateDto);

    void delete(int id);

    List<GiftCertificateDto> filterGiftCertificatesList(String tagName,
                                                        String certName,
                                                        String description);

    List<GiftCertificateDto> orderResultList(List<GiftCertificateDto> resultList,
                                             String sortByName,
                                             String sortByDate);
}
