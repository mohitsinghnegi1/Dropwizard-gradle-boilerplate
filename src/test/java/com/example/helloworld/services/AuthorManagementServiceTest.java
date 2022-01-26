package com.example.helloworld.services;

import com.example.helloworld.entity.Author;
import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.repository.MySqlDb;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AuthorManagementServiceTest {

    MySqlDb db;
    AuthorManagementService ams;

    @Before
    public void init() {
        db = Mockito.mock(MySqlDb.class);
        ams = new AuthorManagementService(db);
    }

    @Test
    public void testGetAuthorById() {
        IAuthorInfo author = new Author("authorTestId");

        when(db.addNewAuthor(author)).thenReturn(true).thenReturn(false);
        assertTrue(ams.addNewAuthor(author));
        assertFalse(ams.addNewAuthor(author));

    }

}