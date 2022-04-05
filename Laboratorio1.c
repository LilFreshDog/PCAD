#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>

float** createMatrix(int rows, int cols){
  //create the matrix
  float** matrix = (float**) malloc(rows * sizeof(float*));
  for(int i = 0;i<rows;i++) {
    matrix[i] = (float*) malloc(cols * sizeof(float));
  }

  //fill the matrix with random values
  for(int i = 0;i<rows;i++) {
    for(int j = 0;j<cols;j++) {
      matrix[i][j] = (float)(rand()%100);
    }
  }

  return matrix;
}

void printMatrix(float** matrix, int rows, int cols){
  for(int i = 0;i<rows;i++) {
    for(int j = 0;j<cols;j++) {
      printf("%f  -   ",matrix[i][j]);
    }
    printf("\n");
  }
}


int* rowsForThread(int rows, int 🧵){

  int* rowsForThread = (int*) malloc(🧵 * sizeof(int));
  memset(rowsForThread, 0, 🧵*sizeof(rowsForThread[0]));
  int i = 0;
  while(rows > 0){
    rowsForThread[i] = ++rowsForThread[i];
    i++;
    rows--;
    if(i == 🧵)i=0;  
  }
  return rowsForThread;
}

void mulRowsCols(float** matrixA, int rowsA, int colsA, float** matrixB, int colsB, float** matrixAB,int row_start,int row_end){
  int row_col_result = 0;
  //cicliamo sulla porzione di righe della prima matrice che ci interessa
  for(int i = row_start; i < row_end; i++){
    //cicliamo per il numero di colonne della seconda matrice perchè dobbiamo moltiplicare la singola riga per tutte le colonne di B
    for(int k = 0; k < colsB; k++ ){
      row_col_result = 0;
      //cicliamo su tutti gli elementi della riga attuale cos' da ottenere il valore da mettere nella matrice AB
      for (int j = 0; j < colsA; j++){
        row_col_result += matrixA[i][j] * matrixB[j][k]; 
      }
      matrixAB[i][k] = row_col_result;
    }
  }
}


float** mulMatrices(float** matrix1, float** matrix2, int rows1, int cols1, int rows2, int cols2, int 🧵){  

  //genero la matrice finale
  float** matrix3 = (float**) malloc(rows1 * sizeof(float*));
  for(int i = 0;i<rows1;i++) {
    matrix3[i] = (float*) malloc(cols2 * sizeof(float));
  }

  int *rows_for_threads = rowsForThread(rows1, 🧵);

  //creating an array of threads
  pthread_t * my_threads = (pthread_t*) malloc(🧵 * sizeof(pthread_t));
  for (int i = 0; i < 🧵; i++){
    pthread_t my_thread;
    my_threads[i] = my_thread;
  }

  return matrix3;
}

int main(int argc, char const *argv[])
{
  srand(time(0));
  int rowsA, colsA, rowsB, colsB, rowsC, colsC, 🧵;
  printf("🚀Insert number of rows and columns of first matrix (pressing enter between the two):\n");
  scanf("%d", &rowsA);
  scanf("%d", &colsA);
  
  //il numero di righe della matrice B deve essere uguale al numero di colonne della matrice A
  printf("🚀Insert number of columns of first matrix (pressing enter between the two):\n");
  rowsB = colsB;
  scanf("%d", &colsB);

  //ricaviamo il numero di righe e di colonne per la matrice C
  rowsC = colsB;
  colsC = rowsA;

  printf("🚀Insert the number of threads:\n");
  scanf("%d", &🧵);

  float **matrixA = createMatrix(rowsA,colsA);
  float **matrixB = createMatrix(rowsB,colsB);
  float **matrixC = createMatrix(rowsC,colsC);
  
  printf("\n\n\n");
  printMatrix(matrixA, rowsA, colsA);
  printf("\n\n\n");
  printMatrix(matrixB, rowsB, colsB);
  printf("\n\n\n");
  //printf("RESULT");
  //printf("\n\n\n");
  //float **matrixAB = mulMatrices(matrixA, matrixB, rows1, cols1, rows2, cols2, 🧵);
  //printMatrix(matrixAB, rows1, cols2);
  //float **sas = createMatrix(rows1,cols2);
  //mulRowsCols(matrixA,rows1,cols1, matrixB, cols2, sas,0,rows1);
  //printMatrix(sas,rows1, cols2);
  return 0;
}
