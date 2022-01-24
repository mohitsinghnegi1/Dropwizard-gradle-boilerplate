package com.example.helloworld.services;

import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.interfaces.IDbService;
import com.google.inject.Inject;

import java.util.List;

public class AuthorManagementService {

    private final IDbService dbService;

    @Inject
    public AuthorManagementService(IDbService dbService) {
        this.dbService = dbService;
    }

    public IAuthorInfo getAuthorById(String id) {
        return dbService.getAuthorById(id);
    }

    public List<IAuthorInfo> getAuthors() {
        return dbService.getAuthors();
    }

    public List<IBookInfo> getBooksOfAuthor(String id) {
        return dbService.getBooksOfAuthor(id);
    }

    public boolean addNewAuthor(IAuthorInfo author) {
        return dbService.addNewAuthor(author);
    }
}
