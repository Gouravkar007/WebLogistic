package com.parthi.logistic.common.model;

import java.util.Locale;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Properties {

    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_RETURNED = "RETURNED";
    public static final String STATUS_SOLD = "SOLD";
    private static Scanner sc = new Scanner(System.in);

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parthi_erp");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static Scanner getSacnnerInstance() {
        sc.useLocale(Locale.US);
        return sc;
    }

    public static EntityManager getDBConnection() {
        return entityManager;
    }

    public static void closeDBConnection() {
        entityManager.close();
        entityManagerFactory.close();
        ;
    }

}
