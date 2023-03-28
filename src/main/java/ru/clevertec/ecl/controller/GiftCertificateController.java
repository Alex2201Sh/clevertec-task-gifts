package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.service.GiftCertificateService;
import ru.clevertec.ecl.service.ObjectSerializer;
import ru.clevertec.ecl.utils.CustomBeanUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gifts")
public class GiftCertificateController {

    private final GiftCertRepository repository;
    private final ObjectSerializer serializer;
    private final GiftCertificateService service;

    @Autowired
    public GiftCertificateController(GiftCertRepository repository, ObjectSerializer serializer, GiftCertificateService service) {
        this.repository = repository;
        this.serializer = serializer;
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<String> gelAllCertificates(@RequestParam(value = "tag_name", required = false) String tagName, @RequestParam(value = "cert_name", required = false) String certName, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "sort_by_name", required = false) String sortByName, @RequestParam(value = "sort_by_date", required = false) String sortByDate) {
        List<GiftCertificate> resultList;
        if (tagName == null && certName == null && description == null) {
            resultList = repository.findAll();
        } else {
            resultList = CustomBeanUtils.filterGiftCertificatesList(repository, tagName, certName, description);
        }
        if (sortByName != null || sortByDate != null) {
            resultList = CustomBeanUtils.orderResultList(resultList, sortByName, sortByDate);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(resultList), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getGiftCertificateById(@PathVariable("id") int id) throws MyException {
        GiftCertificateDto giftCertificate;
        HttpHeaders headers = new HttpHeaders();
        try {
            giftCertificate = service.findById(id);
        } catch (MyException exc) {
            return new ResponseEntity<>(
                    serializer.objectToJson(new HashMap<>(Map.of(exc.getMessage(), exc.getCode()))),
                    headers, HttpStatus.NOT_FOUND);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(giftCertificate), headers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        GiftCertificate certificate = repository.save(giftCertificate);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/gifts/{id}").buildAndExpand(certificate.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(serializer.objectToJson(certificate));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody GiftCertificate giftCertificate) throws MyException {
        GiftCertificateDto giftCertificateFromDb;
        HttpHeaders headers = new HttpHeaders();
        try {
            giftCertificateFromDb = service.findById(id);
        } catch (MyException exc) {
            return new ResponseEntity<>(
                    serializer.objectToJson(exc), headers, HttpStatus.BAD_REQUEST);
        }
        CustomBeanUtils.copyProperties(giftCertificate, giftCertificateFromDb);
        GiftCertificate saved = service.save(giftCertificateFromDb);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(saved), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
    }

}
