package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Book {

    @Length(max = 4)
    private String id;
    @Length(max = 4)
    private String authorId;

    public Book() {
       // Needed by Jackson deserialization
    }

    public Book(String id,String authorId) {
        this.id = id;
        this.authorId = authorId;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getAuthorId() {
        return authorId;
    }
}
