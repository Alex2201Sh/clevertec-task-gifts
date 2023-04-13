package ru.clevertec.ecl.service;

import ru.clevertec.ecl.bean.Order;
import ru.clevertec.ecl.bean.User;

import java.util.List;

public interface OrderService {
    List<Order> findByUser(User user);
    Order createOrderByCertificateIds(List<Integer> ids);

}
