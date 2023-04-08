package ru.clevertec.ecl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.User;
import ru.clevertec.ecl.service.ObjectSerializer;
import ru.clevertec.ecl.service.UserService;
import ru.clevertec.ecl.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final ObjectSerializer serializer;


    @Autowired
    public UserController(UserServiceImpl service, ObjectSerializer serializer) {
        this.service = service;
        this.serializer = serializer;
    }

    @GetMapping()
    public ResponseEntity<String> gelAllCertificates() {
        List<User> allUsers = service.getAllUsers();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(allUsers), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        User userById = service.getUserById(id);
        List<GiftCertificate> giftCertificatesByUser = service.getGiftCertificatesByUser(userById);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(
                new Object[]{userById, giftCertificatesByUser}),
                headers, HttpStatus.OK);
    }
}
