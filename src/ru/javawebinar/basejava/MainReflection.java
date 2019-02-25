package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        Class clss = r.getClass();
        Method method = clss.getDeclaredMethod("toString");
        Object t = clss.newInstance();
        Object m = method.invoke(t);
        System.out.println(m);
// TODO : invoke r.toString via reflection
        System.out.println(r);
    }
}