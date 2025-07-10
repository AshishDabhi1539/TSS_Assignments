package com.tss.test;

import java.util.Scanner;

public class MatrixMultiplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int matrix[][] = new int[3][3];		
		
		readMatrix(matrix,scanner);
		//multiplicationMatrix(m,n,matrix1,matrix2);

	}

	private static void readMatrix(int[][] matrix, Scanner scanner) {
		System.out.println("Enter Matrix Elements : ");
		for(int i = 0; i < 3; i++) {
			readRow(matrix,scanner,i);
		}
		
		System.out.println("Matrix : ");
		resultMatrix(matrix,scanner);
	}

	private static void readRow(int[][] matrix, Scanner scanner, int i) {
		for(int j = 0; j < 3; j++) {
			matrix[i][j] = scanner.nextInt();
		}
	}

	private static void resultMatrix(int[][] matrix, Scanner scanner) {
		for(int i = 0; i<3; i++) {
			printRow(matrix,scanner,i);			
		}
	}

	private static void printRow(int[][] matrix, Scanner scanner, int i) {
		for(int j=0; j<3; j++) {
			System.out.print(matrix[i][j] + "\t");
		}
		System.out.println();
	}

	/*
	 * private static void multiplicationMatrix(int[][] matrix) {
	 * 
	 * int multiplication[][] = new int[3][3];
	 * 
	 * for(int i=0; i<3; i++) { for(int j=0; j<3; j++) { for(int k=0; k<3; k++) {
	 * multiplication[i][j] += matrix1[i][k] * matrix2[k][j]; } } }
	 * 
	 * System.out.println("\nResult of Multiplication Matrix : ");
	 * resultMatrix(m,n,multiplication); }
	 */
}
