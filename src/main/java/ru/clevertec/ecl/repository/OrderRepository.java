package ru.clevertec.ecl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.ecl.bean.Order;
import ru.clevertec.ecl.bean.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
