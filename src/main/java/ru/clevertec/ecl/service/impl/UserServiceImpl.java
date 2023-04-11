package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Order;
import ru.clevertec.ecl.bean.User;
import ru.clevertec.ecl.repository.GiftCertRepository;
import ru.clevertec.ecl.repository.UserRepository;
import ru.clevertec.ecl.service.OrderService;
import ru.clevertec.ecl.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderService orderService;
    private final GiftCertRepository giftCertRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrderService orderService, GiftCertRepository giftCertRepository) {
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.giftCertRepository = giftCertRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderService.findByUser(user);
    }

    @Override
    public User createOrder(User user, List<GiftCertificate> certificateList) {
        certificateList = certificateList.stream()
                .map(giftCertificate ->
                        giftCertRepository
                                .findById(giftCertificate.getId())
                                .orElse(null))
                .toList();
        Order order = new Order();
        order.setUser(user);
        order.setPurchaseTimeStamp(LocalDateTime.now());
        order.setCost((float) order.getCertificateList()
                .stream()
                .mapToDouble(GiftCertificate::getPrice)
                .sum());
        order.setCertificateList(certificateList);
        user.addOrder(order);
        return userRepository.save(user);
    }

    @Override
    public Order getOrder(Integer orderId) {
        return null;
    }
}
