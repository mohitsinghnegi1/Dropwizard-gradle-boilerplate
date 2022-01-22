package com.example.helloworld.orm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Book {

    Book(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        em.find(Book.class,"book1");
    }

}
