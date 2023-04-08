package ru.clevertec.ecl.service;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    List<GiftCertificate> getGiftCertificatesByUser(User user);

    User createOrder(User user, Integer giftCertificateId);

    GiftCertificate getOrder(User user, Integer orderId);
}
