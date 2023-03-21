package ru.clevertec.ecl;

import ru.clevertec.ecl.bean.GiftCertificate;
import ru.clevertec.ecl.dao.GiftCertDao;

public class Main {
    public static void main(String[] args) {
        GiftCertDao dao = new GiftCertDao();
        GiftCertificate byId = dao.getById(2);
        System.out.println(byId);
    }
}
