package com.example.helloworld.models;

import com.example.helloworld.interfaces.IShape;

public class Square implements IShape {

    @Override
    public long getArea() {
        return 10;
    }
}
