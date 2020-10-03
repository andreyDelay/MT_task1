package com.andrey.task1;

public class C implements Runnable {
    Foo third;

    C(Foo f) {
        third = f;
        new Thread(this).start();
    }

    public void run() {
        try {
            third.third(() -> System.out.print("third"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
