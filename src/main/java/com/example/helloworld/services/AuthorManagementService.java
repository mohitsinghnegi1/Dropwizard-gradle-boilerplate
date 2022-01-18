package com.example.helloworld.services;

import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.interfaces.IDbService;

import java.util.List;

public class AuthorManagementService {

    private final IDbService dbService;

    public AuthorManagementService(IDbService dbService){
        this.dbService = dbService;
    }

    public IAuthorInfo getAuthorById(String id){
        return dbService.getAuthorById(id);
    }

    public List<IAuthorInfo> getAuthors(){
        return dbService.getAuthors();
    }

    public boolean addNewAuthor(IAuthorInfo author){
        return dbService.addNewAuthor(author);
    }
}
