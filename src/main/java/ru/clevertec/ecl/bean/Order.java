package ru.clevertec.ecl.bean;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString(exclude = "certificateList")
@EqualsAndHashCode(exclude = "certificateList")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float cost;

    private LocalDateTime purchaseTimeStamp;

    @ManyToOne
    private User user;

    @OneToMany
    private List<GiftCertificate> certificateList = new ArrayList<>();

    public void addCertificate(GiftCertificate certificate) {
        certificateList.add(certificate);
    }

}
