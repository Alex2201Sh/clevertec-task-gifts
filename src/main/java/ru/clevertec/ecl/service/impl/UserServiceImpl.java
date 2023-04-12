package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Order;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.bean.User;
import ru.clevertec.ecl.repository.GiftCertRepository;
import ru.clevertec.ecl.repository.UserRepository;
import ru.clevertec.ecl.service.OrderService;
import ru.clevertec.ecl.service.UserService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public List<Tag> getMostWidelyUsedTagWithHighestCost(Integer userId) {

        List<Order> orderList = getUserById(userId).getOrderList();

        List<Tag> tagList = orderList
                .stream()
                .flatMap(order -> order.getCertificateList()
                        .stream())
                .flatMap(giftCertificate -> giftCertificate.getTagList()
                        .stream())
                .toList();

        Map<Tag, Long> countMap = tagList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long maxTagCount = countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElse(0L);

        List<Tag> widelyUsedTags = countMap.entrySet().stream()
                .filter(e -> e.getValue().equals(maxTagCount))
                .map(Map.Entry::getKey)
                .toList();

        return widelyUsedTags.stream().filter(tag -> {
                    Order order1 = orderList.stream().filter(order ->
                                    order.getCertificateList().stream()
                                            .flatMap(giftCertificate -> giftCertificate.getTagList().stream())
                                            .toList().contains(tag))
                            .max(Comparator.comparing(Order::getCost))
                            .get();
                    List<Tag> collect = order1.getCertificateList()
                            .stream().flatMap(giftCertificate -> giftCertificate.getTagList().stream())
                            .toList();
                    return collect.contains(tag);
                }
        ).toList();
    }
}
