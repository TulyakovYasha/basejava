package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class MainStreams {
    private static int sum;

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        for (Integer integer : oddOrEven(integers)) {
            System.out.println(integer);
        }
        int[] values = {9, 8};
        System.out.println(minValue(values));

    }

    public static int minValue(int[] values) {
        OptionalInt value = Arrays.stream(values).distinct().sorted().reduce((x, y) -> Integer.parseInt(String.format("%d%d", x, y)));
        return value.getAsInt();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {

        List<Integer> list = new ArrayList<>();
        for (Integer ints : integers) {
            sum = sum + ints;
        }
        integers.forEach(integer -> {
            if(sum % 2 == 0){
                if(integer % 2 == 0){
                    list.add(integer);
                }
            }else {
                if(integer % 2 != 0){
                    list.add(integer);
                }
            }
        });
        return list;
    }

}
