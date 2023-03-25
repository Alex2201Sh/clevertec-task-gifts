package ru.clevertec.ecl.service;

import org.springframework.stereotype.Service;

@Service
public class GiftCertificateService {

//    private final TagRepository tagRepository;
//    private final GiftCertRepository giftCertRepository;

//    private final CertificateTagRepository certificateTagRepository;

//    @Autowired
//    public GiftCertificateService(TagRepository tagRepository, GiftCertRepository giftCertRepository, CertificateTagRepository certificateTagRepository) {
//        this.tagRepository = tagRepository;
//        this.giftCertRepository = giftCertRepository;
//        this.certificateTagRepository = certificateTagRepository;
//    }

//    public List<GiftCertificate> findGiftCertificatesByTagName(String tagName) {
//        Tag byName = tagRepository.findByName(tagName);
//        List<CertificateTag> byTagId = certificateTagRepository.findByTagId(byName.getId());
//        return byTagId.stream().map(CertificateTag::getGiftCertificate).toList();
//    }
}
