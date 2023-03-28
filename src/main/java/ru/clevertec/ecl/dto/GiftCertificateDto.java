package ru.clevertec.ecl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.postgresql.util.PGInterval;

import java.time.LocalDateTime;

@Data
@Builder(builderMethodName = "aCertificateDto", toBuilder = true, setterPrefix = "set")
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class GiftCertificateDto {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private PGInterval duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
