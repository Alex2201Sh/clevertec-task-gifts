package ru.clevertec.ecl.utils;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.clevertec.ecl.bean.GiftCertificate;

@UtilityClass
public class HibernateUtil {
    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(GiftCertificate.class);
        return configuration.buildSessionFactory();
    }
}
