package ru.clevertec.ecl.utils;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomBeanUtils {
    public static void copyProperties(GiftCertificate giftCertificate, GiftCertificate giftCertificateFromDb) {
        if (giftCertificate.getName() != null) {
            giftCertificateFromDb.setName(giftCertificate.getName());
        }
        if (giftCertificate.getDescription() != null) {
            giftCertificateFromDb.setDescription(giftCertificate.getDescription());
        }
        if (giftCertificate.getPrice() != null) {
            giftCertificateFromDb.setPrice(giftCertificate.getPrice());
        }
        if (giftCertificate.getDuration() != null) {
            giftCertificateFromDb.setDuration(giftCertificate.getDuration());
        }
        if (giftCertificate.getCreateDate() != null) {
            giftCertificateFromDb.setCreateDate(giftCertificate.getCreateDate());
        }
        if (giftCertificate.getLastUpdateDate() != null) {
            giftCertificateFromDb.setLastUpdateDate(giftCertificate.getLastUpdateDate());
        }
    }

    public static List<GiftCertificate> filterGiftCertificatesList(GiftCertRepository repository, String tagName, String certName, String description) {
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
        return resultList;
    }

    public static List<GiftCertificate> orderResultList(List<GiftCertificate> resultList, String sortByName, String sortByDate) {
        Comparator<GiftCertificate> firstComparator = sortByName != null ?
                ((o1, o2) -> ("desc".equalsIgnoreCase(sortByName) ? -1 : 1) * o1.getName().compareTo(o2.getName())) :
                sortByDate != null ?
                        (o1, o2) -> ("desc".equalsIgnoreCase(sortByDate) ? -1 : 1) * o1.getCreateDate().compareTo(o2.getCreateDate()) :
                        Comparator.comparing(GiftCertificate::getId);

        Comparator<GiftCertificate> secondComparator = sortByName != null ?
                (o1, o2) -> ("desc".equalsIgnoreCase(sortByDate) ? -1 : 1) * o1.getCreateDate().compareTo(o2.getCreateDate()) :
                Comparator.comparing(GiftCertificate::getId);

        return resultList.stream()
                .sorted(firstComparator)
                .sorted(secondComparator)
                .toList();
    }
}
