package com.example.helloworld.services;

import com.example.helloworld.models.Bindings;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DependencyManager {
    private static  Injector injector;

    public static void init(Bindings bindings){
        injector = Guice.createInjector( bindings);
    }

    public static Injector getInjector() {
        if(injector==null){
            System.out.println("Please Initialize the injector");
        }
        return injector;
    }
}
