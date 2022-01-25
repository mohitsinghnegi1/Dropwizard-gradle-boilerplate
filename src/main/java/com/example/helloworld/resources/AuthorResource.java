package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Author;
import com.example.helloworld.interfaces.IAuthorInfo;
import com.example.helloworld.interfaces.IBookInfo;
import com.example.helloworld.services.AuthorManagementService;
import com.google.inject.Inject;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorManagementService ams;

    @Inject
    public AuthorResource(AuthorManagementService ams) {
        this.ams = ams;
    }

    @GET
    @Timed
    public IAuthorInfo getAuthorById(@QueryParam("authorId") String authorId) {
        return ams.getAuthorById(authorId);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Author addNewAuthor(@NotNull Author author) {
        System.out.println("Inside add New Author");
        System.out.println("author : " + author.id);
        if (ams.addNewAuthor(author) == false) {
            System.out.println(String.format("Author %s not added", author.getId()));
            return null;
        }
        return author;
    }

    @GET
    @Timed
    @Path("/{authorId}/books")
    public List<IBookInfo> getBooksOfAuthor(@PathParam("authorId") String authorId) {
        return ams.getBooksOfAuthor(authorId);
    }
}
