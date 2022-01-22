package com.example.helloworld.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                '}';
    }
}
