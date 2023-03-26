package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.utils.CustomBeanUtils;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftCertificateController {

    private final GiftCertRepository repository;

    @Autowired
    public GiftCertificateController(GiftCertRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<GiftCertificate> gelAllCertificates(@RequestParam(value = "tag_name", required = false) String tagName,
                                                    @RequestParam(value = "cert_name", required = false) String certName,
                                                    @RequestParam(value = "description", required = false) String description,
                                                    @RequestParam(value = "sort_by_name", required = false) String sortByName,
                                                    @RequestParam(value = "sort_by_date", required = false) String sortByDate
    ) {
        List<GiftCertificate> resultList;
        if (tagName == null && certName == null && description == null) {
            resultList = repository.findAll();
        } else {
            resultList = CustomBeanUtils.filterGiftCertificatesList(repository, tagName, certName, description);
        }
        if (sortByName != null || sortByDate != null) {
            resultList = CustomBeanUtils.orderResultList(resultList, sortByName, sortByDate);
        }
        return resultList;
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable("id") int id) throws MyException {
        try {
            return repository.findById(id);
        } catch (MyException exc) {
            throw new MyException("Gift certificate with id " + id + " doesn't exist");
        }
    }

    @PostMapping()
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        return repository.save(giftCertificate);
    }

    @PatchMapping("/{id}")
    public GiftCertificate update(@PathVariable("id") int id,
                                  @RequestBody GiftCertificate giftCertificate) throws MyException {
        GiftCertificate giftCertificateFromDb = null;
        try {
            giftCertificateFromDb = repository.findById(id);
        } catch (MyException e) {
            throw new MyException("Gift certificate with id " + id + " doesn't exist");
        }
        CustomBeanUtils.copyProperties(giftCertificate, giftCertificateFromDb);
        return repository.save(giftCertificateFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
    }

}
