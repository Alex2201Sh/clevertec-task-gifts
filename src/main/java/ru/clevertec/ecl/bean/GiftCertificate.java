package ru.clevertec.ecl.bean;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.Period;

@Data
public class GiftCertificate {

    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Period duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
