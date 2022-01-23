package com.example.helloworld;

import com.example.helloworld.healthchecks.TemplateHealthCheck;
import com.example.helloworld.models.Bindings;
import com.example.helloworld.resources.AuthorResource;
import com.example.helloworld.resources.BookResource;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.services.DependencyManager;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BookStoreApplication extends Application<BookStoreConfiguration> {
    public static void main(String[] args) throws Exception {

        System.out.println("hello world");

//        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//            EntityManager em = emf.createEntityManager();
////
//            Books book1 = em.find(Books.class, "bookId13");
//            System.out.println("foundBook " + book1);
//            // TO set we need to have a transaction
////            em.getTransaction().begin();
////            em.persist(new Books("bookId13", "authorId9"));
////            em.getTransaction().commit();
//
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//        }


        try {
            new BookStoreApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
