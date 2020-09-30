package com.andrey.task1;

public class B implements Runnable{
    Foo second;

    B(Foo f) {
        second = f;
        new Thread(this).start();
    }

    public void run() {
        second.second(() -> System.out.print("second"));
    }
}
