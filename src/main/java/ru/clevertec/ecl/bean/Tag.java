package ru.clevertec.ecl.bean;

import lombok.Data;

//import javax.persistence.ManyToMany;
//import java.util.HashSet;
//import java.util.Set;

@Data
public class Tag {
    private int id;
    private String name;

//    @ManyToMany(mappedBy = "gift_certificates")
//    private Set<GiftCertificate> certificateSet = new HashSet<>();
}
