package com.andrey.task1;

public class B implements Runnable{
    Foo second;

    B(Foo f) {
        second = f;
        new Thread(this).start();
    }

    public void run() {
        try {
            second.second(() -> System.out.print("second"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
