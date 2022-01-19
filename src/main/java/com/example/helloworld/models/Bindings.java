package com.example.helloworld.models;

import com.example.helloworld.api.Book;
import com.example.helloworld.interfaces.IDbService;
import com.example.helloworld.repository.InMemoryDb;
import com.example.helloworld.resources.AuthorResource;
import com.example.helloworld.resources.BookResource;
import com.example.helloworld.services.AuthorManagementService;
import com.example.helloworld.services.BookManagementService;
import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Scopes;

public class Bindings extends AbstractModule {
    @Override
    protected void configure() {
        bind(IDbService.class).to(InMemoryDb.class).in(Scopes.SINGLETON);
        bind(BookResource.class).in(Scopes.SINGLETON);
        bind(AuthorResource.class).in(Scopes.SINGLETON);
        bind(AuthorManagementService.class).in(Scopes.SINGLETON);
        bind(BookManagementService.class).in(Scopes.SINGLETON);
    }
}
