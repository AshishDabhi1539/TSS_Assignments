package com.tss.basics.array;

import java.util.Scanner;

public class BubbleShort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Number : ");
        int elements = scanner.nextInt();

        int number[] = new int[elements];

        System.out.println("Enter Elements : ");
        for (int i = 0; i < elements; i++) {
            number[i] = scanner.nextInt();
        }

        sortArr(number);
        System.out.print("Sorted Elements : ");
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i] + " ");
        }
    }

    private static void sortArr(int[] number) {
        for (int i = 0; i < number.length - 1; i++) {
            for (int j = 0; j < number.length - 1 - i; j++) {
                if (number[j] > number[j + 1]) {
                    int temp = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = temp;
                }
            }
        }
    }
}
