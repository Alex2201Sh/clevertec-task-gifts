package ru.clevertec.ecl.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.config.SpringConfig;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.dao.GiftCertRepositoryImpl;

@Service
public class GiftCertificateService {
//    public GiftCertRepository getRepository() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        JdbcTemplate bean = context.getBean(JdbcTemplate.class);
//        return new GiftCertRepositoryImpl(bean);
//    }
}
