/* matix_util.c */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "matrix_util.h"

float **createMatrix(int rows, int cols)
{
	// create the matrix
	float **matrix = (float **)malloc(rows * sizeof(float *));
	for (int i = 0; i < rows; i++)
	{
		matrix[i] = (float *)malloc(cols * sizeof(float));
	}

	// fill the matrix with random values
	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < cols; j++)
		{
			matrix[i][j] = (float)rand() / (float)(RAND_MAX / 10.0);
		}
	}

	return matrix;
}

float **createMatrixOfZeros(int rows, int cols)
{
	// create the matrix
	float **matrix = (float **)malloc(rows * sizeof(float *));
	for (int i = 0; i < rows; i++)
	{
		matrix[i] = (float *)malloc(cols * sizeof(float));
		memset(matrix[i], 0, cols * sizeof(float));
	}
	return matrix;
}

void printMatrix(float **matrix, int rows, int cols)
{
	for (int i = 0; i < rows; i++)
	{
		printf("[ ");
		for (int j = 0; j < cols; j++)
		{
			j == cols - 1 ? printf("%f ]\n", matrix[i][j]) : printf("%f | ", matrix[i][j]);
		}
	}
	printf("\n");
}
