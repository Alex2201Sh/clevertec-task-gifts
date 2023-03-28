package ru.clevertec.ecl.utils;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;

public class CustomBeanUtils {

    private CustomBeanUtils() {
    }

    /**
     * Utility method for copying only not null parameters.
     * @param giftCertificate - given Object
     * @param giftCertificateFromDb - target Object
     */
    public static void copyProperties(GiftCertificate giftCertificate, GiftCertificateDto giftCertificateFromDb) {
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

}
