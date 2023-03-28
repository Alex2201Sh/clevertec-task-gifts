package ru.clevertec.ecl.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.TagDto;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final TagMapper tagMapper;
    private final GiftCertificateMapper giftCertificateMapper;

    public Tag convert(TagDto dto) {
        return tagMapper.toModel(dto);
    }

    public TagDto convert(Tag entity) {
        return tagMapper.toDto(entity);
    }

    public GiftCertificate convert(GiftCertificateDto dto) {
        return giftCertificateMapper.toModel(dto);
    }

    public GiftCertificateDto convert(GiftCertificate entity) {
        return giftCertificateMapper.toDto(entity);
    }
}
