package com.example.algorithm.ch02;

public class PrimeNumber {

    public static void main(String[] args) {
        int oldPrime = calOldPrime();
        System.out.println("old 나눗셈 진행 횟수 : "  + oldPrime);
        System.out.println();

        int newPrime = calNewPrime();
        System.out.println("new 나눗셈 진행 횟수 "+ newPrime);

    }

    private static int calNewPrime() {
        int counter = 0;
        int pointer = 0;
        int[] prime = new int[500];

        prime[pointer++] = 2;

        for (int i = 3; i <= 1000; i = i + 2) {
            int j;

            for (j = 1; j < pointer; j++) {
                counter++;

                if (i % prime[j] == 0) {
                    break;
                }
            }

            if (pointer == j) {
                prime[pointer++] =  i;
            }
        }


        return counter;
    }

    private static int calOldPrime() {
        int counter = 0; // 나눈 횟수

        // 2부터 n-1까지 어떤 정수로도 나누어 떨어지지 않는 수
        for (int n = 2; n <= 1000; n++) {
            int i = 0;
            for (i = 2; i < n; i++) {
                counter++;
                // 소수 아님
                if (n % i == 0) {
                    break;
                }

            }
        }
        return counter;
    }
}
