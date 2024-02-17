package com.example.algorithm.ch01;

public class Max {

    public static void main(String[] args) {
        Num num = new Num(1, 3, 2, 4242,20);
        int max = Calculator.max(num);

        int min = Calculator.min(num);
        System.out.println("ex)max = " + max);
        System.out.println("ex)min = " + min);

        int middle = Calculator.middle3(num);
        System.out.println("middle = " + middle);

    }

    public static class Num {
        private int num1;
        private int num2;
        private int num3;
        private int num4;
        private int num5;

        public Num(int num1, int num2, int num3, int num4) {
            this.num1 = num1;
            this.num2 = num2;
            this.num3 = num3;
            this.num4 = num4;
        }

        public Num(int num1, int num2, int num3, int num4,int num5) {
            this.num1 = num1;
            this.num2 = num2;
            this.num3 = num3;
            this.num4 = num4;
            this.num5 = num5;
        }

        public int getNum1() {
            return num1;
        }

        public int getNum2() {
            return num2;
        }

        public int getNum3() {
            return num3;
        }

        public int getNum4() {
            return num4;
        }
    }

    static class Calculator {

        public static int max(Num input) {
            int max = input.getNum1();
            if(max < input.getNum2()) max = input.getNum2();
            if(max < input.getNum3()) max = input.getNum3();
            if(max < input.getNum4()) max = input.getNum4();
            return max;
        }

        public static int min(Num input) {
            int min = input.getNum1();
            if(min > input.getNum2()) min = input.getNum2();
            if(min > input.getNum3()) min = input.getNum3();
            if(min > input.getNum4()) min = input.getNum4();
            return min;
        }

        public static int middle3(Num input) {
            // 중앙값을 구한다
            int num1 = input.getNum1();
            int num2 = input.getNum2();
            int num3 = input.getNum3();

            int middle = 0;

            if (num1 >= num2) {
                if (num2 >= num3) {
                    middle = num2;
                }
                else if(num1<=num3){
                    middle = num1;
                }
                else middle = num3;
            }
            else if (num1 > num3) {
                middle = num3;
            } else {
                middle = num2;
            }


            return middle;
        }
    }

}
