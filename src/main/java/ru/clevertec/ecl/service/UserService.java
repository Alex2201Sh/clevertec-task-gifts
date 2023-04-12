package ru.clevertec.ecl.service;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Order;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.bean.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    List<Order> getOrdersByUser(User user);

    User createOrder(User user, List<GiftCertificate> certificateList);

    List<Tag> getMostWidelyUsedTagWithHighestCost(Integer userId);
}
