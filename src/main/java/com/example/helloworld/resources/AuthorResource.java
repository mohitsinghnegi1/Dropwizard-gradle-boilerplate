package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Author;
import com.example.helloworld.interfaces.IAuthorInfo;
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

@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorManagementService ams;
    private final BookManagementService bms;

    @Inject
    public AuthorResource(AuthorManagementService ams, BookManagementService bms){
        this.ams = ams;
        this.bms = bms;
    }

    @GET
    @Timed
    public IAuthorInfo getAuthorById(@QueryParam("authorId") String authorId){
        return ams.getAuthorById(authorId);
    }

    @GET
    @Timed
    @Path("/add")
    public Author addNewAuthor(@QueryParam("authorId") String authorId){
        System.out.println("Inside add New Author");
        Author author = new Author(authorId);
        System.out.println("author : " + author.id);
        ams.addNewAuthor(author);
        return author;
    }
}
