package ru.javawebinar.basejava.Deadlock;

public class Bread {

    public synchronized void takeBread(Knife knife) {
        String name = Thread.currentThread().getName();
        System.out.println("Thread : " + name + " take a bread");


        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс Bread прерван");
        }
        System.out.println("Вызываем метод из knife");
        knife.cut();
    }

    public synchronized void cut() {
        System.out.println("cut bread");
    }
}
