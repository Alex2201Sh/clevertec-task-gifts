package ru.clevertec.ecl;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dao.hibernate.GiftCertRepositoryHibernateImpl;
import ru.clevertec.ecl.exceptions.MyException;

import java.util.List;

public class MainHibernate {
    public static void main(String[] args) throws MyException {
        GiftCertRepositoryHibernateImpl repo = new GiftCertRepositoryHibernateImpl();
        GiftCertificate byId = repo.findById(2);
        List<Tag> tagList = byId.getTagList();
//        List<Tag> tagListByCertificateId = repo.getTagListByCertificateId(2);
        System.err.println(tagList);


//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            GiftCertificate build = GiftCertificate.aCertificateDto()
//                    .setCreateDate(LocalDateTime.now())
//                    .setName("test name")
//                    .setDescription("test descr")
//                    .setDuration(Duration.ofDays(45))
//                    .setPrice(11F)
//                    .build();
//
//            Serializable save = session.save(build);
//            System.err.println(save);
//            GiftCertificate load = session.byId(GiftCertificate.class).load((int) save);
//            System.err.println(load);
//
////            session.
////            GiftCertificate giftCertificate = session.get(GiftCertificate.class, 1);
////            System.err.println(giftCertificate);
//            session.getTransaction().commit();
//    }


    }
}
