package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.service.GiftCertificatesService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificatesService {

    private final Mapper mapper;
    private final GiftCertRepository repository;

    @Autowired
    public GiftCertificateServiceImpl(Mapper mapper, GiftCertRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> giftCertificates = repository.findAll();
        return giftCertificates.stream().map(mapper::convert).toList();
    }

    @Override
    public List<GiftCertificateDto> findByPartOfName(String partOnName) {
        List<GiftCertificate> byPartOfName = repository.findByPartOfName(partOnName);
        return byPartOfName.stream().map(mapper::convert).toList();
    }

    @Override
    public List<GiftCertificateDto> findByPartOfDescription(String partOnName) {
        List<GiftCertificate> byPartOfDescription = repository.findByPartOfDescription(partOnName);
        return byPartOfDescription.stream().map(mapper::convert).toList();
    }

    @Override
    public List<GiftCertificateDto> findCertificateByTagName(String tagName) {
        List<GiftCertificate> byTagName = repository.findCertificateByTagName(tagName);
        return byTagName.stream().map(mapper::convert).toList();
    }

    public GiftCertificateDto findById(int id) throws MyException {
        return mapper.convert(repository.findById(id));
    }

    public GiftCertificateDto save(GiftCertificateDto giftCertificateDto) {
        GiftCertificate saved = repository.save(mapper.convert(giftCertificateDto));
        return mapper.convert(saved);
    }

    @Override
    public int delete(int id) {
        return repository.delete(id);
    }

    /**
     * Method filter data with given parameters.
     * @param tagName - part of Tag name.
     * @param certName - part of GiftCertificate field "name"
     * @param description - part of GiftCertificate field "description"
     * @return - list of filtered objects.
     */

    @Override
    public List<GiftCertificateDto> filterGiftCertificatesList(String tagName,
                                                               String certName,
                                                               String description) {
        List<GiftCertificate> resultList = new ArrayList<>();
        if (tagName != null) {
            resultList.addAll(repository.findCertificateByTagName(tagName));
        }
        if (certName != null) {
            if (resultList.isEmpty()) {
                resultList.addAll(repository.findByPartOfName(certName));
            } else {
                resultList.retainAll(repository.findByPartOfName(certName));
            }
        }
        if (description != null) {
            if (resultList.isEmpty()) {
                resultList.addAll(repository.findByPartOfDescription(description));
            } else {
                resultList.retainAll(repository.findByPartOfDescription(description));
            }
        }
        resultList = resultList.stream().distinct().toList();
        return resultList.stream().map(mapper::convert).toList();
    }

    /**
     * Method order result list by given parameters.
     *
     * @param resultList - given list.
     * @param sortByName - in case of "desc" ordering will be in decreasing order,
     *                   in any another not empty case - in ascending order.
     * @param sortByDate - in case of "desc" ordering will be in decreasing order,
     *                   *                   in any another not empty case - in ascending order.
     * @return - ordered given list.
     */
    public List<GiftCertificateDto> orderResultList(List<GiftCertificateDto> resultList,
                                                    String sortByName,
                                                    String sortByDate) {
        Comparator<GiftCertificateDto> firstComparator;
        if (sortByName != null) {
            firstComparator = ((o1, o2) -> ("desc".equalsIgnoreCase(sortByName) ? -1 : 1) *
                    o1.getName().compareTo(o2.getName()));
        } else {
            if (sortByDate != null)
                firstComparator = (o1, o2) -> ("desc".equalsIgnoreCase(sortByDate) ? -1 : 1) *
                        o1.getCreateDate().compareTo(o2.getCreateDate());
            else firstComparator = Comparator.comparing(GiftCertificateDto::getId);
        }

        Comparator<GiftCertificateDto> secondComparator = sortByName != null ?
                (o1, o2) -> {
                    if ("desc".equalsIgnoreCase(sortByDate))
                        return -1 * o1.getCreateDate().compareTo(o2.getCreateDate());
                    return o1.getCreateDate().compareTo(o2.getCreateDate());
                } :
                Comparator.comparing(GiftCertificateDto::getId);

        return resultList.stream()
                .sorted(firstComparator)
                .sorted(secondComparator)
                .toList();
    }


}
