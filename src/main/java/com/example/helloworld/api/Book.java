package com.example.helloworld.api;

import com.example.helloworld.interfaces.IBookInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Book implements IBookInfo {

    @Length(max = 4)
    public String id;
    @Length(max = 4)
    public String authorId;

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
