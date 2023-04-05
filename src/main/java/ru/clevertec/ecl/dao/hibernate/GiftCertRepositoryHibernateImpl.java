package ru.clevertec.ecl.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dao.GiftCertRepository;
import ru.clevertec.ecl.exceptions.MyException;
import ru.clevertec.ecl.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

@Component
@Primary
public class GiftCertRepositoryHibernateImpl implements GiftCertRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<GiftCertificate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<GiftCertificate> resultList = session.createQuery("SELECT a FROM GiftCertificate a",
                    GiftCertificate.class).getResultList();
            resultList.forEach(giftCertificate ->
                    giftCertificate.setTagList(getTagListByCertificateId(
                            giftCertificate.getId()
                    )));
            return resultList;
        }
    }

    @Override
    public List<GiftCertificate> findByPartOfName(String partOfName) {
        try (Session session = sessionFactory.openSession()) {
            Query<GiftCertificate> query = session.createQuery(
                    "SELECT a FROM GiftCertificate a WHERE name LIKE concat('%',:PN,'%')",
                    GiftCertificate.class);
            query.setParameter("PN", partOfName);
            List<GiftCertificate> resultList = query.getResultList();
            resultList.forEach(giftCertificate ->
                    giftCertificate.setTagList(getTagListByCertificateId(
                            giftCertificate.getId()
                    )));
            return resultList;
        }

    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        try (Session session = sessionFactory.openSession()) {
            Query<GiftCertificate> query = session.createQuery(
                    "SELECT a FROM GiftCertificate a WHERE description LIKE concat('%',:PN,'%')",
                    GiftCertificate.class);
            query.setParameter("PN", partOfDescription);
            List<GiftCertificate> resultList = query.getResultList();
            resultList.forEach(giftCertificate ->
                    giftCertificate.setTagList(getTagListByCertificateId(
                            giftCertificate.getId()
                    )));
            return resultList;
        }
    }

    @Override
    public List<GiftCertificate> findCertificateByTagName(String tagName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery("SELECT t FROM Tag t WHERE name = :TN", Tag.class);
            query.setParameter("TN", tagName);
            Tag tagByName = query.uniqueResult();

            Query query2 = session.createQuery(
                    "SELECT t.certificateList FROM Tag t WHERE t.id = :TID");
            query2.setParameter("TID", tagByName.getId());
            List<GiftCertificate> resultList = (List<GiftCertificate>) query2.getResultList();
            resultList.forEach(giftCertificate ->
                    giftCertificate.setTagList(getTagListByCertificateId(
                            giftCertificate.getId()
                    )));
            return resultList;
        }
    }

    @Override
    public GiftCertificate findById(int id) throws MyException {
        try (Session session = sessionFactory.openSession()) {
            GiftCertificate giftCertificate = session.byId(GiftCertificate.class).load(id);
            giftCertificate.setTagList(getTagListByCertificateId(id));
            return giftCertificate;
        }
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Serializable saved = session.save(giftCertificate);
            giftCertificate.setTagList(getTagListByCertificateId((Integer) saved));
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

    private List<Tag> getTagListByCertificateId(int certificateId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "SELECT gc.tagList FROM GiftCertificate gc WHERE gc.id = :CID");
            query.setParameter("CID", certificateId);
            return query.getResultList();
        }
    }
}
