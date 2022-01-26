package com.example.helloworld.repository;

import com.example.helloworld.entity.Author;
import com.example.helloworld.entity.Books;
import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.models.Bindings;
import com.example.helloworld.services.DependencyManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MySqlDbTest {

    private MySqlDb db;

    @Before
    public void init() {
        DependencyManager.init(new Bindings());
        db = DependencyManager.getInjector().getInstance(MySqlDb.class);
    }

    @Test
    public void testGetAuthorById() {

        IAuthorInfo authorInfo = db.getAuthorById("authorId100");
        assertTrue(authorInfo instanceof IAuthorInfo || authorInfo == null);
    }

    @Test
    public void testGetAuthors() {

        List<IAuthorInfo> authors = db.getAuthors();

        assertNotNull(authors);

        if (authors.size() > 0) {
            assertTrue(authors.get(0) instanceof IAuthorInfo);
        } else {
            assertTrue(authors.size() == 0);
        }

    }

    @Test
    public void testGetBooksOfAuthor() {

        List<IBookInfo> books = db.getBooksOfAuthor("authorId2");
        assertNotNull(books);

        if (books.size() > 0) {
            assertTrue(books.get(0) instanceof IBookInfo);
        } else {
            assertTrue(books.size() == 0);
        }
    }

    @Test
    public void testGetAllBooks() {

        List<IBookInfo> books = db.getAllBooks();

        assertNotNull(books);

        if (books.size() > 0) {
            assertTrue(books.get(0) instanceof IBookInfo);
        } else {
            assertTrue(books.size() == 0);
        }
    }

    @Test
    public void testAddNewBook() {

        int prevNoOfBooks = db.getAllBooks().size();

        boolean isBookAdded = db.addNewBook(new Books("Book30", new Author("author30")));

        int curNoOfBooks = db.getAllBooks().size();

        if (isBookAdded) {
            assertTrue(prevNoOfBooks + 1 == curNoOfBooks);
        } else {
            assertTrue(prevNoOfBooks == curNoOfBooks);
        }

    }

    @Test
    public void testAddNewAuthor() {

        int prevNoOfAuthors = db.getAuthors().size();

        boolean isAuthorAdded = db.addNewAuthor(new Author("AuthorId100"));

        int curNoOfAuthors = db.getAuthors().size();

        if (isAuthorAdded) {
            assertTrue(prevNoOfAuthors + 1 == curNoOfAuthors);
        } else {
            assertTrue(prevNoOfAuthors == curNoOfAuthors);
        }
    }
}