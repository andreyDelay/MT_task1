package com.andrey.task1;

public class A implements Runnable{
    Foo first;

    A(Foo f) {

        first = f;
        new Thread(this).start();
    }

    public void run() {
        first.first(() -> System.out.print("first"));
    }
}
