package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton{
    private ConcurrentSingleton(){};

    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference( new ConcurrentSingleton());

    public static AtomicReference<ConcurrentSingleton> getInstance(){
        if(instance == null){
            instance = new AtomicReference<>(new ConcurrentSingleton());
        }
        return instance;
    }
}