package telran.numbers;

import java.util.Arrays;

public class MatrixInt {
public static int matrixSum(int matrix[][]) {
	int sum = 0;
	
	for(int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix[i].length; j++) {
			sum = sum + matrix[i][j];
		}
	}
	return sum;
}
/**
 * 
 * @param matrix
 * @param row
 * @param index
 * @return new matrix with inserted row at the given index
 * assumptions: the given matrix exists with at least one row
 */
public static int[][] insertRow(int matrix[][], int row[], int index) {
	int res[][] = new int[matrix.length + 1][];
	if (row.length != matrix[0].length) {
		row = Arrays.copyOf(row, matrix[0].length);
	}
	System.arraycopy(matrix, 0, res, 0, index);
	res[index] = row;
	System.arraycopy(matrix, index, res, index + 1, matrix.length - index);
	return res;
}
public static int [][] insertColumn(int matrix[][], int column[], int index) {
	if (column.length != matrix.length) {
		column = Arrays.copyOf(column, matrix.length);
	}
	int res[][] = Arrays.copyOf(matrix, matrix.length);
	for (int i = 0; i < res.length; i++) {
		res[i] = ArrayInt.insertNumber(res[i], index, column[i]);
	}
	return res;
}
public static int[][] removeRow(int matrix[][], int index) {
	int res[][] = new int[matrix.length - 1][];
	System.arraycopy(matrix, 0, res, 0, index);
	System.arraycopy(matrix, index + 1, res, index, res.length - index);
	return res;
}
public static int [][] removeColumn(int matrix[][], int index) {
	int res[][] = Arrays.copyOf(matrix, matrix.length);
	for (int i = 0; i < res.length; i++) {
		res[i] = ArrayInt.removeNumber(res[i], index);
	}
	return res;
}
public static int [][] transposition(int matrix[][]) {
	int res[][] = new int[matrix[0].length][matrix.length];
	for(int i = 0; i < res.length; i++) {
		for(int j = 0; j < res[i].length; j++) {
			res[i][j] = matrix[j][i];
		}
	}
	return res;
}
	public static char [][] transposition(char matrix[][]) {
		char res[][] = new char[matrix[0].length][matrix.length];
		for(int i = 0; i < res.length; i++) {
			for(int j = 0; j < res[i].length; j++) {
				res[i][j] = matrix[j][i];
			}
		}
		return res;
	}
}
