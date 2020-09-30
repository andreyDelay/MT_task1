package com.andrey.task1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    ReentrantLock lock;
    private AtomicInteger ai = new AtomicInteger();

    Foo() {
        this.lock = new ReentrantLock(false);
        ai.getAndSet(1);
    }

    public void first(Runnable r1) {
        lock.lock();
        r1.run();
        ai.getAndIncrement();
        lock.unlock();
    }

    public void second(Runnable r2) {
        while (ai.get() != 2) {
            try {
                lock.tryLock(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        r2.run();
        ai.getAndIncrement();
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    public void third(Runnable r3) {
        while (ai.get() != 3) {
            try {
                lock.tryLock(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        r3.run();
        ai.getAndIncrement();
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}

