package ru.clevertec.ecl.bean;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.postgresql.util.PGInterval;
import ru.clevertec.ecl.utils.Interval;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
//@Builder(builderMethodName = "aCertificateDto", toBuilder = true, setterPrefix = "set")
//@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "GiftCertificate")
@Table(name = "gift_certificates")
//@TypeDef(name = "interval", typeClass = ауInterval.class)
public class GiftCertificate implements BaseEntity<Integer>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Float price;
    @Type(type = "interval")
    private PGInterval duration;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
}
