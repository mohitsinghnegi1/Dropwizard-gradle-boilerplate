package com.example.helloworld.repository;

import com.example.helloworld.api.Author;
import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.interfaces.IDbService;

import java.util.*;

public class InMemoryDb implements IDbService {

    private static Hashtable<String,IAuthorInfo> authorsInfo = null;
    private static Hashtable<String,IBookInfo> booksInfo = null;
    private static Hashtable<String,ArrayList<IBookInfo>> authorToBooksMap = null;

    public InMemoryDb(){
        authorsInfo = new Hashtable<String,IAuthorInfo>();
        booksInfo = new Hashtable<String,IBookInfo>();
        authorToBooksMap = new Hashtable<String,ArrayList<IBookInfo>>();
    }

    @Override
    public IAuthorInfo getAuthorById(String id) {
        return authorsInfo.getOrDefault(id, new Author("Dummy"));
    }

    @Override
    public List<IAuthorInfo> getAuthors() {

        List authors = new ArrayList<IAuthorInfo>();

        for (Map.Entry<String, IAuthorInfo> entry : authorsInfo.entrySet()) {
            IAuthorInfo author = entry.getValue();
            authors.add(author);
        }

        return authors;
    }

    @Override
    public List<IBookInfo> getBooksOfAuthor(String authorId) {
        return authorToBooksMap.getOrDefault(authorId,new ArrayList<IBookInfo>());
    }

    @Override
    public List<IBookInfo> getAllBooks() {
        List books = new ArrayList<IBookInfo>();

        for (Map.Entry<String, IBookInfo> entry : booksInfo.entrySet()) {
            IBookInfo book = entry.getValue();
            books.add(book);
        }
        return books;
    }

    public void printHashTable(Hashtable dict){

        dict.forEach((key,value)->{
            System.out.println("key => "+key);
        });

    }

    @Override
    public boolean addNewBook(IBookInfo book) {
        System.out.println("book id addNew Book "+book.getId());
        if(booksInfo.containsKey(book.getId())){
            System.out.println("Duplicate Book"+book.getId());
            return false;
        }

        String authorId = book.getAuthorId();
        boolean isAuthorExist = authorsInfo.containsKey(authorId);

        System.out.println(authorId);
        System.out.println(isAuthorExist);
        // Book must have some author
        if(!isAuthorExist){
            printHashTable(authorsInfo);
            System.out.println("Author does not exist so cant add the book with id "+book.getId() );
            return false;
        }

        ArrayList<IBookInfo> existingBook =  authorToBooksMap.getOrDefault(book.getAuthorId(), new ArrayList<IBookInfo>());
        existingBook.add(book);
        authorToBooksMap.put(book.getAuthorId(),existingBook);
        booksInfo.put(book.getId(),book);
        return true;
    }

    @Override
    public boolean addNewAuthor(IAuthorInfo author) {
        if(authorsInfo.containsKey(author.getId())){
            return false;
        }
        authorsInfo.put(author.getId(),author);
        return true;
    }
}
