package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.bean.Order;
import ru.clevertec.ecl.bean.User;
import ru.clevertec.ecl.repository.OrderRepository;
import ru.clevertec.ecl.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public Order createOrderByCertificateIds(List<Integer> ids) {
//        repository.
        return null;
    }
}
