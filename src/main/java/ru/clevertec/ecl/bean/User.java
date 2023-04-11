package ru.clevertec.ecl.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "orderList")
@EqualsAndHashCode(exclude = "orderList")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    @OneToMany
    @JoinTable(
            name = "users_orders",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order){
        orderList.add(order);
    }

}
