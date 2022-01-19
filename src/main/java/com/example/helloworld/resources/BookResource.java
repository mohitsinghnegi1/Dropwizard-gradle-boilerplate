package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Book;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.services.AuthorManagementService;
import com.example.helloworld.services.BookManagementService;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final AuthorManagementService ams;
    private final BookManagementService bms;

    @Inject
    public BookResource(AuthorManagementService ams, BookManagementService bms){
        this.ams = ams;
        this.bms = bms;
    }

    @GET
    @Timed
    public List<IBookInfo> getAllBooks(){
        return bms.getAllBooks();
    }

    // TODO : Make this a post api
    @GET
    @Timed
    @Path("/add")
    public Book addNewBook(@QueryParam("id") String id, @QueryParam("authorId") String authorId){
        System.out.println("Inside add Book");
        Book book = new Book(id,authorId);
        System.out.println("bookId : " + book.id);
        bms.addNewBook(book);
        return book;
    }


    @GET
    @Timed
    @Path("/author")
    public List<IBookInfo> getBooksOfAuthor(@QueryParam("authorId") String authorId){
        return bms.getBooksOfAuthor(authorId);
    }
}
