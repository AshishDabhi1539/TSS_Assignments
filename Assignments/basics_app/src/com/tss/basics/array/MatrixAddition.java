package com.tss.basics.array;

import java.util.Scanner;

public class MatrixAddition {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter m : ");
		int m = scanner.nextInt();
		
		System.out.print("Enter n : ");
		int n = scanner.nextInt();
		
		int matrix1[][] = new int[m][n];
		int matrix2[][] = new int[m][n];		
		
		readMatrix(m,n,matrix1,matrix2,scanner);
		additionMatrix(m,n,matrix1,matrix2);
		multiplicationMatrix(m,n,matrix1,matrix2);

	}

	private static void readMatrix(int m, int n, int[][] matrix1, int[][] matrix2, Scanner scanner) {
		System.out.println("Enter 1st Matrix Elements : ");
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				matrix1[i][j] = scanner.nextInt();
			}
		}
		
		System.out.println("Enter 2nd Matrix Elements : ");
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				matrix2[i][j] = scanner.nextInt();
			}
		}
		
		System.out.println("1st Matrix : ");
		resultMatrix(m,n,matrix1);
		System.out.println("2nd Matrix : ");
		resultMatrix(m,n,matrix2);
	}

	private static void resultMatrix(int m, int n, int[][] matrix) {
		for(int i = 0; i<m; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static void additionMatrix(int m,int n,int[][] matrix1, int[][] matrix2) {
		int sum[][] = new int[m][n];
		
		for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
		
		System.out.println("Result of Addition Matrix : ");
		resultMatrix(m,n,sum);
	}
	
	private static void multiplicationMatrix(int m, int n, int[][] matrix1, int[][] matrix2) {
		if(m!=n) {
			System.out.println("\nFor multiplication Please enter same value for m and n");
			return;
		}
		
		int multiplication[][] = new int[m][n];
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					multiplication[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		
		System.out.println("\nResult of Multiplication Matrix : ");
		resultMatrix(m,n,multiplication);
	}


}
