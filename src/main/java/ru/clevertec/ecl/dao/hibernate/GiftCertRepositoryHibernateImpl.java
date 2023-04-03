package ru.clevertec.ecl.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

@Component
public class GiftCertRepositoryHibernateImpl implements GiftCertRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<GiftCertificate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT a FROM GiftCertificate a",
                    GiftCertificate.class).getResultList();
        }
    }

    @Override
    public List<GiftCertificate> findByPartOfName(String partOfName) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query<GiftCertificate> query = session.createQuery(
                    "SELECT a FROM GiftCertificate a WHERE name LIKE concat('%',:PN,'%')",
                    GiftCertificate.class);
            query.setParameter("PN", partOfName);
            List<GiftCertificate> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }

    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query<GiftCertificate> query = session.createQuery(
                    "SELECT a FROM GiftCertificate a WHERE description LIKE concat('%',:PN,'%')",
                    GiftCertificate.class);
            query.setParameter("PN", partOfDescription);
            List<GiftCertificate> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public List<GiftCertificate> findCertificateByTagName(String tagName) {
//        try (Session session = sessionFactory.openSession()) {
//            session.getTransaction().begin();
//            Query<GiftCertificate> query = session.createQuery(
//                    "SELECT a from GiftCertificate a where id " +
//                            "IN (SELECT certificate_id FROM certificate_tag WHERE tag_id = " +
//                            "(SELECT tags.id FROM tags WHERE name = :TN))",
//                    GiftCertificate.class);
//            query.setParameter("TN", tagName);
//            List<GiftCertificate> resultList = query.getResultList();
//            session.getTransaction().commit();
//            return resultList;
//        }
        return null;
    }

    @Override
    public GiftCertificate findById(int id) throws MyException {
        try (Session session = sessionFactory.openSession()) {
            return session.byId(GiftCertificate.class).load(id);
        }
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Serializable saved = session.save(giftCertificate);
            session.getTransaction().commit();
            try {
                return findById((Integer) saved);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int delete(int id) {
        int i;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE FROM GiftCertificate WHERE id = :ID");
            query.setParameter("ID", id);
            i = query.executeUpdate();
            session.getTransaction().commit();
        }
        return i;
    }
}
