package ru.clevertec.ecl.bean;

import lombok.Data;
import org.postgresql.util.PGInterval;

import java.time.LocalDateTime;

@Data
public class GiftCertificate {

    private Integer id;
    private String name;
    private String description;
    private Float price;
    private PGInterval duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
