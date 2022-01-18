package com.example.helloworld.api;

import com.example.helloworld.interfaces.IAuthorInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Author implements IAuthorInfo {

    public String id = null;

    public Author(String authorId){
        id = authorId;
    }

    @JsonProperty
    public String getId() {
        return id;
    }
}
