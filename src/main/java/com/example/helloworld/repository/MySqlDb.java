package com.example.helloworld.repository;

import com.example.helloworld.entity.Author;
import com.example.helloworld.entity.Books;
import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.interfaces.IDbService;
import com.example.helloworld.services.JPAUtil;
import com.google.inject.Inject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDb implements IDbService {

    private String AUTHOR_TABLE = "Author";
    private String BOOKS_TABLE = "Books";
    private EntityManagerFactory emf;

    @Inject
    public MySqlDb(JPAUtil jpaUtil) {

        try {
            emf = jpaUtil.getEntityManagerFactory();

        } catch (Exception e) {
            System.out.println("Inside MySql Db catch block");
            e.printStackTrace();
        }
    }


    @Override
    public IAuthorInfo getAuthorById(String id) {
        EntityManager em = emf.createEntityManager();
        IAuthorInfo authorInfo = em.find(Author.class, id);
        System.out.println("Author found : with id " + id + " is " + authorInfo);
        return authorInfo;
    }

    @Override
    public List<IAuthorInfo> getAuthors() {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT authors from " + AUTHOR_TABLE + " authors ";
        TypedQuery tq = em.createQuery(query, Author.class);
        try {
            return tq.getResultList();

        } catch (NoResultException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<IBookInfo> getBooksOfAuthor(String id) {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT books from " + BOOKS_TABLE + " books WHERE books.author.id = :authId";
        TypedQuery tq = em.createQuery(query, Books.class);
        tq.setParameter("authId", id);
        try {
            return tq.getResultList();

        } catch (NoResultException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    public <T> void printList(List<T> list) {
        list.forEach(item -> {
            System.out.println(item);
        });
    }

    @Override
    public List<IBookInfo> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT books from " + BOOKS_TABLE + " books ";
        TypedQuery tq = em.createQuery(query, Books.class);
        try {
            System.out.println();
            List<IBookInfo> books = tq.getResultList();
            printList(books);

            return books;

        } catch (NoResultException e) {
            e.printStackTrace();
            return new ArrayList<IBookInfo>();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean addNewBook(IBookInfo book) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            Author author = em.getReference(Author.class, book.getAuthorId());
            Books book1 = new Books(book.getId(), author);

            et.begin();
            em.persist(book1);
            et.commit();

            em.close();
            return true;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            em.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addNewAuthor(IAuthorInfo author) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            Author author1 = new Author(author.getId());

            et.begin();
            em.persist(author1);
            et.commit();
            em.close();
            return true;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();

            }
            e.printStackTrace();
            em.close();
            System.out.println("Duplicate entry of author exist for key 'author.PRIMARY'" + author.getId());
            return false;
        }
    }
}
