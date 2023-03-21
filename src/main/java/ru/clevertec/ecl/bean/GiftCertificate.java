package ru.clevertec.ecl.bean;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GiftCertificate {
    private int id;
    private String name;
    private String description;
    private float price;
    private int duration;
    private LocalDate createDate;
    private LocalDate lastUpdateDate;
}
