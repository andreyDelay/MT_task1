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
        try {
            lock.lock();
            r1.run();
            ai.getAndIncrement();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable r2) {
        try {
            while (ai.get() != 2) {
                try {
                    lock.tryLock(100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            r2.run();
            ai.getAndIncrement();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public void third(Runnable r3) {
        try {
            while (ai.get() != 3) {
                try {
                    lock.tryLock(100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            r3.run();
            ai.getAndIncrement();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }
}

