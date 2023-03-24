package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;

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
    public List<GiftCertificate> gelAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable("id") GiftCertificate giftCertificate) {
        return giftCertificate;
    }

    @PostMapping()
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        return repository.save(giftCertificate);
    }
//
//    @PatchMapping("/{id}")
//    public Product update(@PathVariable("id") Product productFromDb,
//                          @RequestBody Product product) {
//        BeanUtils.copyProperties(product, productFromDb, "id");
//        return productDaoSpring.save(productFromDb);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") Product product) {
//        productDaoSpring.delete(product);
//    }
}
