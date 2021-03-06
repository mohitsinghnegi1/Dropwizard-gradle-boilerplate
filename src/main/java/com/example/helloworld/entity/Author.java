package com.example.helloworld.entity;

import com.example.helloworld.interfaces.IAuthorInfo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Author implements IAuthorInfo {

    @Id
    private String id;

    @OneToMany(mappedBy = "author")
    private List<Books> books;

    public Author(String id) {
        this.id = id;
    }

    public Author() {

    }

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
