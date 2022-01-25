package com.example.helloworld.interfaces;

import com.example.helloworld.entity.Author;

public interface IBookInfo {
    String id = null;
    Author author = null;

    String getId();

    String getAuthorId();
}
