package ru.job4j.pools.example;

import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) {
        int[] array = new int[]{5, 6, 4, 7, 3, 8, 2, 9, 1, 0};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(MergeSort.sort(array)));
    }
}
