package com.example.algorithm.ch02;

import java.util.Arrays;

public class Swap {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        reverse(arr);
        System.out.println("arr = " + Arrays.toString(arr));

        System.out.println(sumOf(arr));

        int[] copyArr = new int[arr.length];
        copy(arr, copyArr);
        System.out.println("copyArr = " + Arrays.toString(copyArr));

    }

    static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            swap(a, i, a.length - i - 1);
        }
    }

    static void swap(int[] arr,int i,int last) {
        int temp = arr[i];
        arr[i] = arr[last];
        arr[last] = temp;
    }

    static int sumOf(int[] arr){
        int size = arr.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static void copy(int[] from,int[] to) {
        int size = from.length;
        for (int i = 0; i < size; i++) {
            to[i] = from[i];
        }
    }


}
