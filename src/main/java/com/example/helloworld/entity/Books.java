package com.example.helloworld.entity;

import com.example.helloworld.interfaces.IBookInfo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Books implements IBookInfo {

    @Id
    private String id;

    @ManyToOne
    private Author author;


    public Books(String id, Author author) {
        this.id = id;
        this.author = author;
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
        return author.getId();
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id='" + id + '\'' +
                ", authorId='" + author.getId() + '\'' +
                '}';
    }
}
