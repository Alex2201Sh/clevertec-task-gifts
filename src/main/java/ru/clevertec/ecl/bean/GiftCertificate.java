package ru.clevertec.ecl.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
//@Entity
public class GiftCertificate {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private float price;
    private int duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE})
//    @JoinTable(name = "gift_certificates_tags",
//            joinColumns = @JoinColumn(name = "gift_certificate_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private List<Tag> tagList = new ArrayList<>();
}
