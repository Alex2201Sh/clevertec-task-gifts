package ru.clevertec.ecl.dto;

import lombok.Data;
import org.postgresql.util.PGInterval;

import java.time.LocalDateTime;

@Data
public class GiftCertificateDto {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private PGInterval duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
