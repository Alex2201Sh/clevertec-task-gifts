package ru.clevertec.ecl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import ru.clevertec.ecl.bean.Tag;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
    private Duration duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tagList;
}
