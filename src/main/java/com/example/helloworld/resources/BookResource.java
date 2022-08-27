package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Book;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.services.BookManagementService;
import com.google.inject.Inject;
import org.hibernate.criterion.Example;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookManagementService bms;
    private static final Logger logger = Logger.getLogger("dummyFilter");


    @Inject
    public BookResource(BookManagementService bms) {

        this.bms = bms;
    }

    @GET
    @Timed
    public List<IBookInfo> getAllBooks() {
        logger.info("Fetching books ....");

        return bms.getAllBooks();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Book addNewBook(@NotNull Book book) {
        System.out.println("Inside add Book");
        System.out.println("bookId : " + book.id);

        if (bms.addNewBook(book) == false) {
            System.out.println(String.format("Book %s not added", book.getId()));
            return null;
        }
        return book;
    }
}
