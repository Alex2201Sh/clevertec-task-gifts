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
@ToString(exclude = "certificateList")
@EqualsAndHashCode(exclude = "certificateList")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    @OneToMany
    @JoinTable(
            name = "users_gift_certificates",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")}
    )
    @JsonIgnore
    private List<GiftCertificate> certificateList = new ArrayList<>();

    public void addCertificate(GiftCertificate certificate){
        certificateList.add(certificate);
    }

}
