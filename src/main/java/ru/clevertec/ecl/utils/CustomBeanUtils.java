package ru.clevertec.ecl.utils;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;

public class CustomBeanUtils {

    private CustomBeanUtils() {
    }

    /**
     * Utility method for copying only not null parameters.
     * @param giftCertificateSource - given Object
     * @param giftCertificateDtoTarget - target Object
     */
    public static void copyProperties(GiftCertificate giftCertificateSource, GiftCertificateDto giftCertificateDtoTarget) {
        if (giftCertificateSource.getName() != null) {
            giftCertificateDtoTarget.setName(giftCertificateSource.getName());
        }
        if (giftCertificateSource.getDescription() != null) {
            giftCertificateDtoTarget.setDescription(giftCertificateSource.getDescription());
        }
        if (giftCertificateSource.getPrice() != null) {
            giftCertificateDtoTarget.setPrice(giftCertificateSource.getPrice());
        }
        if (giftCertificateSource.getDuration() != null) {
            giftCertificateDtoTarget.setDuration(giftCertificateSource.getDuration());
        }
        if (giftCertificateSource.getCreateDate() != null) {
            giftCertificateDtoTarget.setCreateDate(giftCertificateSource.getCreateDate());
        }
        if (giftCertificateSource.getLastUpdateDate() != null) {
            giftCertificateDtoTarget.setLastUpdateDate(giftCertificateSource.getLastUpdateDate());
        }
    }

}
