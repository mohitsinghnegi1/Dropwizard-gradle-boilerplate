package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    @GET
    @Timed
    public Book addBook(@QueryParam("id") String id, @QueryParam("authorId") String authorId){
        System.out.println("Inside add Book");
        return new Book(id,authorId);
    }
}
