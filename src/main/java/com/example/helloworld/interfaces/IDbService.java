package com.example.helloworld.interfaces;

import java.util.List;

public interface IDbService {
    IAuthorInfo getAuthorById(String id);
    List<IAuthorInfo> getAuthors();
    List<IBookInfo> getBooksOfAuthor(String id);
    List<IBookInfo> getAllBooks();
    boolean addNewBook(IBookInfo book);
    boolean addNewAuthor(IAuthorInfo author);
}
