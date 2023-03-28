package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.service.CrudService;

import java.util.List;

@Service
public class GiftCertificateService implements CrudService {

    private final Mapper mapper;
    private final GiftCertRepository repository;

    @Autowired
    public GiftCertificateService(Mapper mapper, GiftCertRepository repository) {
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
}
