package app.domain.model;

import static org.junit.Assert.*;

import app.domain.shared.exceptions.MatrixInvalidLines;
import app.domain.shared.exceptions.MatrixInvalidSubSum;
import app.domain.shared.exceptions.VectorInvalidLines;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class MatrixManipulationTest {

    MatrixManipulation matrixManipulation = new MatrixManipulation();

    //Multiply Matrix Vector
    @Test
    public void multiplyMatrixVectorCorrect() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[] vector = {1, 2};
        double[] expected = {5, 11};

        double[] result = matrixManipulation.multiplyMatrixVector(matrix, vector);

        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    @Test
    public void multiplyMatrixVectorWrong() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[] vector = {1, 2};
        double[] expected = {6, 12};

        double[] result = matrixManipulation.multiplyMatrixVector(matrix, vector);

        Assert.assertNotEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    @Test(expected = MatrixInvalidLines.class)
    public void multiplyMatrixVectorInvalidMatrix() {
        double[][] matrix = {};
        double[] vector = {1, 2};

        matrixManipulation.multiplyMatrixVector(matrix, vector);
    }

    @Test(expected = VectorInvalidLines.class)
    public void multiplyMatrixVectorInvalidVector() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[] vector = {};

        matrixManipulation.multiplyMatrixVector(matrix, vector);

    }

    //Multiply Matrix
    @Test
    public void multiplyMatrixCorrect() {
        double[][] matrix1 = {{1, 2}, {3, 4}};
        double[][] matrix2 = {{5, 6}, {7, 8}};
        double[][] expected = {{19, 22}, {43, 50}};

        double[][] result = matrixManipulation.multiplyMatrices(matrix1, matrix2);

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void multiplyMatrixWrong() {
        double[][] matrix1 = {{1, 2}, {3, 4}};
        double[][] matrix2 = {{5, 6}, {7, 8}};
        double[][] expected = {{20, 23}, {44, 51}};

        double[][] result = matrixManipulation.multiplyMatrices(matrix1, matrix2);

        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void multiplyMatrixInvalidMatrix1() {
        double[][] matrix1 = {};
        double[][] matrix2 = {{5, 6}, {7, 8}};

        matrixManipulation.multiplyMatrices(matrix1, matrix2);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void multiplyMatrixInvalidMatrix2() {
        double[][] matrix1 = {{1, 2}, {3, 4}};
        double[][] matrix2 = {};

        matrixManipulation.multiplyMatrices(matrix1, matrix2);
    }

    //Transposed Matrix
    @Test
    public void transposedCorrect() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] expected = {{1, 3}, {2, 4}};

        double[][] result = matrixManipulation.transposedMatrix(matrix);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void transposedWrong() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] expected = {{1, 6}, {2, 4}};

        double[][] result = matrixManipulation.transposedMatrix(matrix);

        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void transposedInvalidMatrix() {
        double[][] matrix = {};

        matrixManipulation.transposedMatrix(matrix);
    }

    //Determinant Matrix
    @Test
    public void determinantCorrect() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double expected = -2;

        double result = matrixManipulation.determinant(matrix);

        Assert.assertEquals(expected, result, 2);
    }

    @Test
    public void determinantWrong() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double expected = 3;

        double result = matrixManipulation.determinant(matrix);

        Assert.assertNotEquals(expected, result, 2);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void determinantInvalidMatrix() {
        double[][] matrix = {};
        double expected = -2;

        matrixManipulation.determinant(matrix);
    }

    //Inverse Matrix
    @Test
    public void inverseCorrect() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] expected = {{-2, 1}, {3 / 2, -1 / 2}};

        double[][] result = matrixManipulation.invertMatrix(matrix);

        int rows = result.length;
        int columns = result[0].length;

        for (int l = 0; l < rows; l++) {
            for (int c = 0; c < columns; c++) {
                result[l][c] = Math.round(result[l][c]);
            }
        }

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void inverseWrong() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] expected = {{-3, 2}, {3, 1}};

        double[][] result = matrixManipulation.invertMatrix(matrix);

        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void inverseMatrixInvalid() {
        double[][] matrix = {};

        matrixManipulation.invertMatrix(matrix);
    }

    //Matrix sum
    @Test
    public void sumCorrect() {
        double[][] firstMatrix = {{1, 2}, {3, 4}};
        double[][] secondMatrix = {{5, 6}, {7, 8}};
        double[][] expected = {{6, 8}, {10, 12}};

        double result[][] = matrixManipulation.sumMatrix(firstMatrix, secondMatrix);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sumWrong() {
        double[][] firstMatrix = {{1, 2}, {3, 4}};
        double[][] secondMatrix = {{5, 6}, {7, 8}};
        double[][] expected = {{7, 8}, {11, 13}};

        double result[][] = matrixManipulation.sumMatrix(firstMatrix, secondMatrix);

        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = MatrixInvalidSubSum.class)
    public void sumMatrixNotEquals() {
        double[][] firstMatrix = {{1, 2}, {3, 4}, {5, 6}};
        double[][] secondMatrix = {{5, 6}, {7, 8}};

        matrixManipulation.sumMatrix(firstMatrix, secondMatrix);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void sumMatrixInvalidA() {
        double[][] firstMatrix = {};
        double[][] secondMatrix = {{5, 6}, {7, 8}};

        matrixManipulation.sumMatrix(firstMatrix, secondMatrix);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void sumMatrixInvalidB() {
        double[][] firstMatrix = {{1, 2}, {3, 4}};
        double[][] secondMatrix = {};

        matrixManipulation.sumMatrix(firstMatrix, secondMatrix);
    }

    //Matrix sub
    @Test
    public void subCorrect() {
        double[][] firstMatrix = {{1, 2}, {3, 4}};
        double[][] secondMatrix = {{5, 6}, {7, 8}};
        double[][] expected = {{-4, -4}, {-4, -4}};

        double result[][] = matrixManipulation.subMatrix(firstMatrix, secondMatrix);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void subWrong() {
        double[][] firstMatrix = {{1, 2}, {3, 4}};
        double[][] secondMatrix = {{5, 6}, {7, 8}};
        double[][] expected = {{7, 8}, {11, 13}};

        double result[][] = matrixManipulation.subMatrix(firstMatrix, secondMatrix);

        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = MatrixInvalidSubSum.class)
    public void subMatrixNotEquals() {
        double[][] firstMatrix = {{1, 2}, {3, 4}, {5, 6}};
        double[][] secondMatrix = {{5, 6}, {7, 8}};

        matrixManipulation.subMatrix(firstMatrix, secondMatrix);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void subMatrixInvalidA() {
        double[][] firstMatrix = {};
        double[][] secondMatrix = {{5, 6}, {7, 8}};

        matrixManipulation.subMatrix(firstMatrix, secondMatrix);
    }

    @Test(expected = MatrixInvalidLines.class)
    public void subMatrixInvalidB() {
        double[][] firstMatrix = {{1, 2}, {3, 4}};
        double[][] secondMatrix = {};

        matrixManipulation.subMatrix(firstMatrix, secondMatrix);
    }

    //Matrix To String
    @Test
    public void matrixToStringCorrect(){
        double[][] matrix = {{1, 2}, {3, 4}};
        String expected = "1.0  2.0  \n3.0  4.0  \n";

        Assert.assertEquals(expected, matrixManipulation.matrixToString(matrix));
    }

    @Test
    public void matrixToStringWrong(){
        double[][] matrix = {{1, 2}, {3, 4}};
        String expected = "2.0  3.0  \n4.0  5.0  \n";

        Assert.assertNotEquals(expected, matrixManipulation.matrixToString(matrix));
    }

    @Test(expected = MatrixInvalidLines.class)
    public void matrixToStringInvalid(){
        double[][] matrix = {};
        matrixManipulation.matrixToString(matrix);
    }
}