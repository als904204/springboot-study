package com.example.algorithm.ch02;

import java.util.Arrays;

public class CardConverter {

    public static void main(String[] args) {
        int value = 59;
        int to = 16;
        char[] storage = new char[100];
        int result = cardConv(value, to, storage);

        System.out.print(value + "를 " + to + "진수로 변환하면 : ");
        for (int i = 0; i < result; i++) {
            System.out.print(storage[i]);
        }
    }
    // 10 진수 to n 진수
    // 10 진수 / n 진수 -> 몫이 0 이 될때까지 반복
    // 10 진수 % n 진수 나머지가 n 진수로 변환된 값
    // x = 값, n = 변환할 진수 (59,16 -> 59를 16진수로 변환)
    static int cardConv(int value, int to, char[] d) {
        int digits = 0;
        String dChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        do {
            d[digits++] = dChar.charAt(value % to);
            value = value / to;
        } while (value != 0);

        for (int i = 0; i < digits / 2; i++) {
            char t = d[i];
            d[i] = d[digits - 1 - i];
            d[digits - 1 - i] = t;
        }
        return digits;
    }

}
