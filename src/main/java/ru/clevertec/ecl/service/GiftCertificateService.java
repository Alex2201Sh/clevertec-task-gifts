package ru.clevertec.ecl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.mapper.Mapper;

@Service
public class GiftCertificateService {

    private final Mapper mapper;
    private final GiftCertRepository repository;

    @Autowired
    public GiftCertificateService(Mapper mapper, GiftCertRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GiftCertificateDto findById(int id) throws MyException {
        return mapper.convert(repository.findById(id));
    }

    public GiftCertificate save(GiftCertificateDto giftCertificateFromDb) {
        return repository.save(mapper.convert(giftCertificateFromDb));
    }
}
