package com.example.helloworld;

import com.example.helloworld.api.Book;
import com.example.helloworld.interfaces.IDbService;
import com.example.helloworld.models.Bindings;
import com.example.helloworld.models.Square;
import com.example.helloworld.repository.InMemoryDb;
import com.example.helloworld.resources.AuthorResource;
import com.example.helloworld.resources.BookResource;
import com.example.helloworld.services.AuthorManagementService;
import com.example.helloworld.services.BookManagementService;
import com.example.helloworld.services.DependencyManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.healthchecks.TemplateHealthCheck;

public class BookStoreApplication extends Application<BookStoreConfiguration> {
    public static void main(String[] args) throws Exception {
        new BookStoreApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<BookStoreConfiguration> bootstrap) {

        DependencyManager.init(new Bindings());
    }

    @Override
    public void run(BookStoreConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

        BookResource bookResource = DependencyManager.getInjector().getInstance(BookResource.class);
        AuthorResource authorResource = DependencyManager.getInjector().getInstance(AuthorResource.class);

        environment.jersey().register(bookResource);
        environment.jersey().register(authorResource);
    }

}
