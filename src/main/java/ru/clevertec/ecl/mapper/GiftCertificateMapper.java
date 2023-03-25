package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dto.GiftCertificateDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public interface GiftCertificateMapper {

//    @Mapping(target="employeeId", source="entity.id")
//    @Mapping(target="employeeName", source="entity.name")
//    GiftCertRepository giftCertificateToEmployeeRepository(GiftCertificate entity);
//
//    @Mapping(target="id", source="dto.employeeId")
//    @Mapping(target="name", source="dto.employeeName")
//    GiftCertificate employeeRepositoryToGiftCertificate(GiftCertRepository repository);

    GiftCertificateMapper INSTANCE = Mappers.getMapper(GiftCertificateMapper.class);

    GiftCertificateDto toDto(GiftCertificate giftCertificate);

}
