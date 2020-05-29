import java.util.Arrays;
import java.util.Scanner;

public class MatrixMultiplication {
	public static void main(String[] args) {
		int count = 1;
		Scanner input = new Scanner(System.in);
		char response = 'Y';
		do {
			System.out.print("Please input m: ");
			int m = input.nextInt();
			System.out.print("Please input n: ");
			int n = input.nextInt();
			System.out.print("Please input the value of R: ");
			int R = input.nextInt();
			int[][] matrix1 = new int[m][n];
			int[][] finalMatrix = new int[m][n];
			int[][] finalSum = new int[m][n];
			System.arraycopy(matrix1, 0, finalMatrix, 0, matrix1.length);

			System.out.println("Please input the matrix: ");
			System.out.print("New Row: ");
			for (int row = 0; row < m; row++) {
				for (int col = 0; col < n; col++) {
					matrix1[row][col] = input.nextInt();
				}
				if (row < (m - 1))
					System.out.print("New Row: ");
			}
			System.out.println();
			System.out.println("*************************************");

			multiplyMatrices(matrix1, finalMatrix, finalSum, R, count);
			System.out.println("*************************************");
			if ((m == R)) {
				if (Arrays.deepEquals(matrix1, finalSum)) {
					System.out.println("The Relation is Transitive");
					displayProduct(finalSum);
				} else {
					System.out.println("The Relation is NOT Transitive");
					System.out.println("The Transitive matrix is: ");
					displayProduct(finalSum);
				}
			}
			System.out.print("Do you wish to input another value? ");
			response = input.next().charAt(0);

		} while (response == 'y' | response == 'Y');

	}

	public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] finalMatrix, int[][] finalSum, int R, int count) {
		int r = firstMatrix[0].length;
		int c = firstMatrix.length;
		int[][] finalProduct = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) 
				finalProduct[i][j] = 0;			
		}
		if (R < 1) {
			return firstMatrix;
		} else {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					for (int k = 0; k < c; k++) {
						finalProduct[i][j] += firstMatrix[i][k] * finalMatrix[k][j];
						finalSum[i][j] += finalMatrix[i][j] + finalProduct[i][j];
					}
					if (finalMatrix[i][j] > 1)
						finalMatrix[i][j] = 1;
					if (finalSum[i][j] > 1)
						finalSum[i][j] = 1;
				}
			}

			System.out.println("The value of R" + count + " is : ");
			displayProduct(finalMatrix);
			return multiplyMatrices(firstMatrix, finalProduct, finalSum, R - 1, count + 1);
		}
	}

	public static void displayProduct(int[][] product) {
		for (int[] row : product) {
			for (int column : row) 
				System.out.print(column + "    ");			
			System.out.println();
		}
		System.out.println();
	}
}