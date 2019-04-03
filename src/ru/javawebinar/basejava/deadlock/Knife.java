package ru.javawebinar.basejava.deadlock;

public class Knife {

    public synchronized void takeKnfie(Bread bread) {
        String name = Thread.currentThread().getName();
        System.out.println("Thread : " + name + " take knife");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс Knife прерван");
        }
        System.out.println("Вызываем метод из Bread");
        bread.cut();

    }

    public synchronized void cut() {
        System.out.println("cut in Knife");
    }
}