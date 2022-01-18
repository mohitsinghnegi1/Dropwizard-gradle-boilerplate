package com.example.helloworld;

import com.example.helloworld.interfaces.IDbService;
import com.example.helloworld.models.Bindings;
import com.example.helloworld.models.Square;
import com.example.helloworld.repository.InMemoryDb;
import com.example.helloworld.resources.AuthorResource;
import com.example.helloworld.resources.BookResource;
import com.example.helloworld.services.AuthorManagementService;
import com.example.helloworld.services.BookManagementService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.healthchecks.TemplateHealthCheck;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector( new Bindings());

        Square sq = injector.getInstance(Square.class);
        System.out.println("Area of square"+ sq.getArea());

        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet

    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

        IDbService inMemoryDb = new InMemoryDb();

        environment.jersey().register(new BookResource(new AuthorManagementService(inMemoryDb),new BookManagementService(inMemoryDb)));
        environment.jersey().register(new AuthorResource(new AuthorManagementService(inMemoryDb),new BookManagementService(inMemoryDb)));
    }

}
