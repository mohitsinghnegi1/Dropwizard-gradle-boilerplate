package com.example.helloworld.repository;

import com.example.helloworld.entity.Author;
import com.example.helloworld.models.Bindings;
import com.example.helloworld.services.DependencyManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
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
    }

    @Test
    public void testGetAuthors() {
    }

    @Test
    public void testGetBooksOfAuthor() {
    }

    @Test
    public void testPrintList() {
    }

    @Test
    public void testGetAllBooks() {
    }

    @Test
    public void testAddNewBook() {
    }

    @Test
    public void testAddNewAuthor() {
        assertTrue(db.addNewAuthor(new Author("AuthorId100")));

        // Existing Author should not be added to database
        assertFalse(db.addNewAuthor(new Author("AuthorId100")));
    }
}