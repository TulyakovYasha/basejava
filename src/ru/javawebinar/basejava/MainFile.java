package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * gkislin
 * 21.07.2016
 */
public class MainFile {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        String filePath1 = "C:\\Users\\Jayton\\projects\\basejava";

        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listAll(filePath1);
    }

    private static void listAll(String path) {
        File dir = new File(path);
        File[] list = dir.listFiles();

        if (list != null) {
            for (File f : list) {
                if (f.isFile()) {
                    System.out.println("     File name: " + f.getName());
                } else {
                    System.out.println("   Directory name : " + f.getName());
                    listAll(f.getPath());
                }
            }
        }
    }
}