package ru.clevertec.ecl.bean;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "tagList")
@EqualsAndHashCode(exclude = "tagList")
@Builder(builderMethodName = "aCertificateDto", toBuilder = true, setterPrefix = "set")
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gift_certificates")
public class GiftCertificate implements BaseEntity<Integer>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32)
    private String name;
    @Column(length = 128)
    private String description;
    private Float price;
    private Duration duration;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "certificate_tag",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")}
    )
    private List<Tag> tagList = new ArrayList<>();
}
