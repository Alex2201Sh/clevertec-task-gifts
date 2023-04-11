package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.service.ObjectSerializer;
import ru.clevertec.ecl.service.impl.GiftCertificateServiceImpl;
import ru.clevertec.ecl.utils.CustomBeanUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gifts")
public class GiftCertificateController {

    private final ObjectSerializer serializer;
    private final GiftCertificateServiceImpl service;

    @Autowired
    public GiftCertificateController(ObjectSerializer serializer, GiftCertificateServiceImpl service) {
        this.serializer = serializer;
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<String> gelAllCertificates(@RequestParam(value = "tag_name", required = false) String tagName,
                                                     @RequestParam(value = "cert_name", required = false) String certName,
                                                     @RequestParam(value = "description", required = false) String description,
                                                     @RequestParam(value = "sort_by_name", required = false) String sortByName,
                                                     @RequestParam(value = "sort_by_date", required = false) String sortByDate) {
        List<GiftCertificateDto> resultList;
        if (tagName == null && certName == null && description == null) {
            resultList = service.findAll();
        } else {
            resultList = service.filterGiftCertificatesList(tagName, certName, description);
        }
        if (sortByName != null || sortByDate != null) {
            resultList = service.orderResultList(resultList, sortByName, sortByDate);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(resultList), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getGiftCertificateById(@PathVariable("id") int id){
        GiftCertificateDto giftCertificate;
        HttpHeaders headers = new HttpHeaders();
        giftCertificate = service.findById(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(giftCertificate), headers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificateDto giftCertificate) {
        GiftCertificateDto certificate = service.save(giftCertificate);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/gifts/{id}")
                .buildAndExpand(certificate.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(serializer.objectToJson(certificate));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id,
                                         @RequestBody GiftCertificate giftCertificate){
        GiftCertificateDto giftCertificateFromDb;
        HttpHeaders headers = new HttpHeaders();
        giftCertificateFromDb = service.findById(id);
        CustomBeanUtils.copyProperties(giftCertificate, giftCertificateFromDb);
        GiftCertificateDto saved = service.save(giftCertificateFromDb);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(saved), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

}
