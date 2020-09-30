package com.andrey.task1;

public class C implements Runnable {
    Foo third;

    C(Foo f) {
        third = f;
        new Thread(this).start();
    }

    public void run() {
        third.third(() -> System.out.print("third"));
    }
}
