package ru.clevertec.ecl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.ecl.utils.HibernateUtil;

public class MainHibernate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Object giftCertificate = session.get("GiftCertificate", 1);
            System.out.println(giftCertificate);
            session.getTransaction().commit();
        }

    }
}
