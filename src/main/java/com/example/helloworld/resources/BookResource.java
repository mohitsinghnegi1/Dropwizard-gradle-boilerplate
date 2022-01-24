package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Book;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.services.BookManagementService;
import com.google.inject.Inject;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookManagementService bms;

    @Inject
    public BookResource(BookManagementService bms) {

        this.bms = bms;
    }

    @GET
    @Timed
    public List<IBookInfo> getAllBooks() {
        return bms.getAllBooks();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Book addNewBook(@NotNull Book book) {
        System.out.println("Inside add Book");
        System.out.println("bookId : " + book.id);
        bms.addNewBook(book);
        return book;
    }
}
