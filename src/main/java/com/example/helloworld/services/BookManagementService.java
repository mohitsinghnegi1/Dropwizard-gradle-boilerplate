package com.example.helloworld.services;

import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.interfaces.IDbService;
import com.google.inject.Inject;

import java.util.List;

public class BookManagementService {

    private final IDbService dbService;

    @Inject
    public BookManagementService(IDbService dbService){
        this.dbService = dbService;
    }

    public List<IBookInfo> getBooksOfAuthor(String id){
        return dbService.getBooksOfAuthor(id);
    }

    public List<IBookInfo> getAllBooks(){
        return dbService.getAllBooks();
    }

    public boolean addNewBook(IBookInfo book){
        return dbService.addNewBook(book);
    }

}
