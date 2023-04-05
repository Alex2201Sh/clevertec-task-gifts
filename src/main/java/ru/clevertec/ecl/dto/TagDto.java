package ru.clevertec.ecl.dto;

import lombok.Data;
import ru.clevertec.ecl.bean.GiftCertificate;

import java.util.List;

@Data
public class TagDto {
    private Integer id;
    private String name;
    private List<GiftCertificate> certificateList;

}
