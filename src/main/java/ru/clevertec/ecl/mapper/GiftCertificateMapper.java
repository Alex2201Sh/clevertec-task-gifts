package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;

@Mapper
public interface GiftCertificateMapper {
    GiftCertificateMapper INSTANCE = Mappers.getMapper(GiftCertificateMapper.class);
    GiftCertificateDto toDto(GiftCertificate giftCertificate);
}
