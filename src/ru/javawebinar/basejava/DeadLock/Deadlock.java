package ru.javawebinar.basejava.DeadLock;

public class Deadlock implements Runnable {

    FirstClass a = new FirstClass();
    SecondClass b = new SecondClass();

    Deadlock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Соперничающий поток");
        t.start();

        a.foo(b); // получить блокировку для объекта a
        // в этом потоке исполнения

        System.out.println("Назад в главный поток");
    }

    public void run() {
        b.bar(a); // получить блокировку для объекта b
        // в другом потоке исполнения
        System.out.println("Назад в другой поток");
    }

    public static void main(String args[]) {
        new Deadlock();
    }
}