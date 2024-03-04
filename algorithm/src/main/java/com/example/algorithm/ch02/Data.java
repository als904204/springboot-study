package com.example.algorithm.ch02;

import com.sun.tools.javac.Main;
import java.util.Scanner;

public class Data {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PhyscData[] x = {
            new PhyscData("강민하",162,0.3),
            new PhyscData("박준서",175,0.7),
            new PhyscData("정경오",162,1.3),
        };

        int[] vdist = new int[V_MAX];

        System.out.println();
    }

    static final int V_MAX = 21;

    static class PhyscData{
        String name;
        int height;
        double vision; // 시력

        public PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }
    }

    static double avgHeight(PhyscData[] data) {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i].height;
        }

        return sum / data.length;
    }

    static void distVision(PhyscData[] data, int[] dist) {
        int i = 0;
        dist[i] = 0;

        for (int j = 0; j < data.length; j++) {
            if (data[i].vision >= 0.0 && data[i].vision <= V_MAX / 10.0) {
                dist[(int) (data[i].vision * 10)]++;
            }
        }
    }
}
