package app.domain.model;

import app.domain.shared.exceptions.MatrixInvalidLines;
import app.domain.shared.exceptions.MatrixInvalidSubSum;
import app.domain.shared.exceptions.VectorInvalidLines;

/**
 * class responsible for the Test
 *
 * @author Ricardo Faria 1201405 and Rodrigo Oliveira 1201500
 */
public class MatrixManipulation {
    public MatrixManipulation() {
        //
    }

    /**
     *
     * @param matrix - matrix
     * @return - number of rows
     */
    private int nRows(double[][] matrix) {
        return matrix.length;
    }

    /**
     *
     * @param matrix - matrix
     * @return - number of columns
     */
    private int nColumns(double[][] matrix) {
        return matrix[0].length;
    }

    /**
     * Method used to verify if the matrix is valid
     * @param matrix - matrix
     */
    private void verifiedMatrix(double[][] matrix) {
        if (nRows(matrix) <= 0) {
            throw new MatrixInvalidLines("The number of rows in the column needs to be positive");
        }

        if (nColumns(matrix) <= 0) {
            throw new MatrixInvalidLines("The number of columns in the column needs to be positive");
        }
    }

    /**
     * Method used to verify if the vector is valid
     * @param vector - vector
     */
    private void verifiedVector(double[] vector) {
        if (vector.length <= 0) {
            throw new VectorInvalidLines("The vector length must be positive");
        }
    }

    /**
     * Method used to check if it is possible to add or subtract two matrices
     * @param firstMatrix - first matrix
     * @param secondMatrix - second matrix
     */
    private void verifiedMatrixSumSub(double[][] firstMatrix, double[][] secondMatrix) {
        verifiedMatrix(firstMatrix);
        verifiedMatrix(secondMatrix);

        if (nRows(firstMatrix) != nRows(secondMatrix)) {
            throw new MatrixInvalidSubSum("The number of rows of the two matrices are different");
        }

        if (nColumns(firstMatrix) != nColumns(secondMatrix)) {
            throw new MatrixInvalidSubSum("The number of columns of the two matrices are different");
        }
    }

    /**
     * Method used to multiply a matrix and a vector
     * @param matrix - matrix
     * @param vector - vector
     * @return vector
     */
    public double[] multiplyMatrixVector(double[][] matrix, double[] vector) {
        verifiedMatrix(matrix);
        verifiedVector(vector);

        double sum;
        int n = nRows(matrix);
        double[] res = new double[n];

        for (int c = 0; c < n; c++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[c][j] * vector[j];
            }
            res[c] = sum;
        }
        return res;
    }

    /**
     * Method used to multiply two matrices
     * @param firstMatrix - first matrix
     * @param secondMatrix - second matrix
     * @return matrix
     */
    public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        verifiedMatrix(firstMatrix);
        verifiedMatrix(secondMatrix);

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        int rows = nRows(result);
        int columns = nColumns(result);
        double sum;

        for (int l = 0; l < rows; l++) {
            for (int c = 0; c < columns; c++) {
                sum = 0;
                for (int i = 0; i < nRows(secondMatrix); i++) {
                    sum += firstMatrix[l][i] * secondMatrix[i][c];
                }
                result[l][c] = sum;
            }
        }
        return result;
    }

    /**
     * Method used to get the transposed matrix
     * @param originalMatrix - original matrix
     * @return transposed matrix of the original matrix
     */
    public double[][] transposedMatrix(double[][] originalMatrix) {
        verifiedMatrix(originalMatrix);
        int rows = nRows(originalMatrix);
        int columns = nColumns(originalMatrix);

        double[][] transposedMatrix = new double[columns][rows];

        for (int l = 0; l < columns; l++) {
            for (int c = 0; c < rows; c++) {
                transposedMatrix[l][c] = originalMatrix[c][l];
            }
        }
        return transposedMatrix;
    }

    /**
     * Method used to get the determinant of a matrix
     * @param matrix - matrix
     * @return determinant
     */
    public double determinant(double[][] matrix) {
        verifiedMatrix(matrix);

        int rows = nRows(matrix);
        int columns = nColumns(matrix);
        double result = 0;

        if (rows == 1) {
            result = matrix[0][0];
            return result;
        }

        if (rows == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            return result;
        }

        for (int i = 0; i < columns; i++) {
            double[][] temp = new double[rows - 1][columns - 1];

            for (int j = 1; j < rows; j++) {
                for (int k = 0; k < columns; k++) {
                    if (k < i) {
                        temp[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temp[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            result += matrix[0][i] * Math.pow(-1, i) * determinant(temp);
        }
        return result;
    }

    /**
     * Method used to get the invert matrix
     * @param matrix - matrix
     * @return inverted matrix
     */
    public double[][] invertMatrix(double[][] matrix) {
        verifiedMatrix(matrix);

        int n = nRows(matrix);
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];

        for (int i = 0; i < n; ++i) {
            b[i][i] = 1;
        }

        gaussian(matrix, index);

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] -= matrix[index[j]][i] * b[index[i]][k];
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / matrix[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= matrix[index[j]][k] * x[k][i];
                }
                x[j][i] /= matrix[index[j]][j];
            }
        }
        return x;
    }

    /**
     * Auxiliary method used in calculating the inverted matrix
     * @param matrix - matrix
     * @param index - index
     */
    private void gaussian(double[][] matrix, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }

        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(matrix[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;

        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;

            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(matrix[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int iTmp = index[j];
            index[j] = index[k];
            index[k] = iTmp;

            for (int i = j + 1; i < n; ++i) {
                double pj = matrix[index[i]][j] / matrix[index[j]][j];
                matrix[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l) {
                    matrix[index[i]][l] -= pj * matrix[index[j]][l];
                }
            }
        }
    }

    /**
     * Method used to obtain an excerpt from the matrix
     * @param firstMatrix - first matrix
     * @param secondMatrix - second matrix
     * @return excerpt of the matrix
     */
    public double[][] subMatrix(double[][] firstMatrix, double[][] secondMatrix) {
        verifiedMatrixSumSub(firstMatrix, secondMatrix);
        int rows = nRows(firstMatrix);
        int columns = nColumns(firstMatrix);
        double[][] result = new double[rows][columns];

        for (int l = 0; l < rows; l++) {
            for (int c = 0; c < columns; c++) {
                result[l][c] = firstMatrix[l][c] - secondMatrix[l][c];
            }
        }
        return result;
    }

    /**
     * Method used to add two matrices
     * @param firstMatrix - first matrix
     * @param secondMatrix - second matrix
     * @return - resulting matrix
     */
    public double[][] sumMatrix(double[][] firstMatrix, double[][] secondMatrix) {
        verifiedMatrixSumSub(firstMatrix, secondMatrix);
        int rows = nRows(firstMatrix);
        int columns = nColumns(firstMatrix);
        double[][] result = new double[rows][columns];

        for (int l = 0; l < rows; l++) {
            for (int c = 0; c < columns; c++) {
                result[l][c] = firstMatrix[l][c] + secondMatrix[l][c];
            }
        }
        return result;
    }

    /**
     * Method used to get a string with the matrix values
     * @param matrix - matrix
     * @return string with the values
     */
    public String matrixToString(double[][] matrix) {
        verifiedMatrix(matrix);
        int rows = nRows(matrix);
        int columns = nColumns(matrix);
        StringBuilder str = new StringBuilder();

        for (int l = 0; l < rows; l++) {
            for (int c = 0; c < columns; c++) {
                str.append(matrix[l][c]).append("  ");
            }
            str.append("\n");
        }

        return str.toString();
    }
}
