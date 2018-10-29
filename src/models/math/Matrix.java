package models.math;

public class Matrix {
	public double[][] data;
	
	public Matrix(double[][] data) {
		this.data = data;
	}
	
	public static Vector multiply(Matrix matrix, Vector vector) {
		int[] result = new int[matrix.data.length];
		for(int i = 0; i < matrix.data.length; i++) {
			for(int j = 0; j < matrix.data[i].length; j++) {
				result[i] += matrix.data[i][j] * vector.get(i);
			}
		}
		return new Vector(result[0], result[1]);
	}
	
	public static Matrix multiply(Matrix x, Matrix y) {
		double[][] data = new double[3][3];
		for(int i = 0; i < x.data.length; i++) {
			for(int j = 0; j < x.data[i].length; j++) {
				for(int k = 0; k < 3; k++) {
					data[i][j] += x.data[k][j] * y.data[i][k];
				}
			}
		}
		return new Matrix(data);
	}
	
	public static Matrix translate(int dx, int dy) {
		double[][] data = {{1, 0, dx}, {0, 1, dy}, {0, 0, 1}};
		return new Matrix(data);
	}
}
