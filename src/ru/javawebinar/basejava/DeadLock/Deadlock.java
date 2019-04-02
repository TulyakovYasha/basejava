package ru.javawebinar.basejava.Deadlock;

public class Deadlock implements Runnable {
    Bread bread = new Bread();
    Knife knife = new Knife();

    Deadlock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Второй поток");
        t.start();
        bread.takeBread(knife);
    }

    @Override
    public void run() {
        knife.takeKnfie(bread);
    }


    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        deadlock.run();
    }

}
