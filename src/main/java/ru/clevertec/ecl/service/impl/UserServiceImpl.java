package ru.clevertec.ecl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.User;
import ru.clevertec.ecl.repository.GiftCertRepository;
import ru.clevertec.ecl.repository.UserRepository;
import ru.clevertec.ecl.service.UserService;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GiftCertRepository giftCertRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GiftCertRepository giftCertRepository) {
        this.userRepository = userRepository;
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
    public List<GiftCertificate> getGiftCertificatesByUser(User user) {
        return giftCertRepository.findGiftCertificatesByUser(user);
    }

    @Override
    public User createOrder(User user, GiftCertificate giftCertificate) {
        user.addCertificate(giftCertificate);
        return userRepository.save(user);
    }

    @Override
    public GiftCertificate getOrder(User user, Integer orderId) {
        return null;
    }
}
