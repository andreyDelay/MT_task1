package com.andrey.task1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private AtomicInteger ai = new AtomicInteger();

    Foo() {
        ai.getAndSet(1);
    }

    public void first(Runnable r1) {
        try {
            lock.lock();
            r1.run();
            ai.getAndIncrement();
            condition.signalAll(); //не могу понять нить выполнения, почему зависает если поставить signal()
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable r2) throws InterruptedException {
        try {
            lock.lock();
                while (ai.get() != 2) {
                    condition.await();
                }

            r2.run();
            ai.getAndIncrement();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable r3) throws InterruptedException {
        try {
            lock.lock();
            while (ai.get() != 3) {
                    condition.await();
            }

            r3.run();
            ai.getAndIncrement();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

