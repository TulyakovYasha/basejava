package ru.javawebinar.basejava;

import java.util.*;
import java.util.stream.Collectors;

public class MainStreams {


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
        int[] values = {3, 1, 2, 3, 1, 2};
        System.out.println(minValue(values));

    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce((x, y) -> y + x * 10).getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0 ? oddOrEvenDelete(true, integers) : oddOrEvenDelete(false, integers);
    }

    private static List<Integer> oddOrEvenDelete(boolean oddOrEven, List<Integer> integers) {
        return integers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)).get(oddOrEven);
    }

}
