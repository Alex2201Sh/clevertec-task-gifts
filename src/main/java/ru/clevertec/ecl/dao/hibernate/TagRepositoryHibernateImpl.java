package ru.clevertec.ecl.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.bean.Tag;
import ru.clevertec.ecl.dao.TagRepository;
import ru.clevertec.ecl.utils.HibernateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class TagRepositoryHibernateImpl implements TagRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Tag> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Tag> resultList = session.createQuery("SELECT t FROM Tag t",
                    Tag.class).getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public Tag findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.byId(Tag.class).load(id);
        }
    }

    @Override
    public Tag findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query<Tag> query = session.createQuery(
                    "SELECT t FROM Tag t WHERE name = :TN",
                    Tag.class);
            query.setParameter("TN", name);
            Tag tag = query.uniqueResult();
            session.getTransaction().commit();
            return tag;
        }
    }

    @Override
    public Tag save(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Serializable saved = session.save(tag);
            session.getTransaction().commit();
            Tag byId = findById((Integer) saved);
            byId.setCertificateList(new ArrayList<>());
            return byId;
        }
    }

    @Override
    public int delete(int id) {
        int i;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE FROM Tag WHERE id = :ID");
            query.setParameter("ID", id);
            i = query.executeUpdate();
            session.getTransaction().commit();
        }
        return i;
    }

    private List<GiftCertificate> getCertificateList(){
        return null;
    }
}
