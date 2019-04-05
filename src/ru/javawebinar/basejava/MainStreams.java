package ru.javawebinar.basejava;

import java.util.*;

public class MainStreams {
    private static int sum = 0;

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
        for (Integer integer : oddOrEven(integers)){
            System.out.println(integer);
        }
        int[] values = {1,1,2,3,2,3};
        System.out.println(minValue(values));

    }
    public static int minValue(int[] values){
        Set<Integer> set = new TreeSet<>();
        Arrays.stream(values).forEach(set::add);
        StringBuilder sb = new StringBuilder();
        set.stream().forEach(sb::append);
        return Integer.parseInt(sb.toString());
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        integers.forEach(integer -> {
            if(integer % 2 == 0){
                even.add(integer);
                sum = sum + integer;
            }else{
                odd.add(integer);
                sum = sum + integer;
            }
        });
        if( sum % 2 == 0){
            integers.removeAll(odd);
        } else {
            integers.removeAll(even);
        }
        return integers;
    }

}
