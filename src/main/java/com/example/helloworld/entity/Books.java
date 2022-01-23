package com.example.helloworld.entity;

import com.example.helloworld.interfaces.IBookInfo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Books implements IBookInfo {

    @Id
    private String id;

    private String authorId;

    public Books(String id, String authorId) {
        this.id = id;
        this.authorId = authorId;
    }

    public Books() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }


    @Override
    public String toString() {
        return "Books{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
