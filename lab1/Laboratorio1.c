#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>

typedef struct {
  float **matrixA;
  int rowsA;
  int colsA;
  float ** matrixB;
  int colsB;
  float **matrixAB;
  int row_start;
  int row_end;
} thread_param_t;

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
    printf("[  ");
    for(int j = 0;j<cols;j++) {
      printf("%f   |   ",matrix[i][j]);
    }
    printf("  ]\n");
    printf("\n");
  }
}


int* rowsForThread(int rows, int threads_num){

  int* rowsForThread = (int*) malloc(threads_num * sizeof(int));
  memset(rowsForThread, 0, threads_num*sizeof(rowsForThread[0]));
  int i = 0;
  while(rows > 0){
    rowsForThread[i] = ++rowsForThread[i];
    i++;
    rows--;
    if(i == threads_num)i=0;  
  }
  return rowsForThread;
}

void* mulRowsCols(void* arguments){

  thread_param_t *param = arguments;
  float row_col_result = 0;
  //cicliamo sulla porzione di righe della prima matrice che ci interessa
  for(int i = param->row_start; i < param->row_end; i++){
    //cicliamo per il numero di colonne della seconda matrice perchè dobbiamo moltiplicare la singola riga per tutte le colonne di B
    for(int k = 0; k < param->colsB; k++ ){
      row_col_result = 0;
      //cicliamo su tutti gli elementi della riga attuale cos' da ottenere il valore da mettere nella matrice AB
      for (int j = 0; j < param->colsA; j++){
        row_col_result += param->matrixA[i][j] * param->matrixB[j][k]; 
      }
      param->matrixAB[i][k] = row_col_result;
    }
  }

  return NULL;
}


float** mulMatrices(float** matrix1, float** matrix2, int rows1, int cols1, int rows2, int cols2, int threads_num){  

  //genero la matrice finale
  float** matrix3 = (float**) malloc(rows1 * sizeof(float*));
  for(int i = 0;i<rows1;i++) {
    matrix3[i] = (float*) malloc(cols2 * sizeof(float));
  }

  //get the number of rows that every thread must handle
  int *rows_for_threads = rowsForThread(rows1, threads_num);

  //creating an array of threads
  pthread_t * my_threads = (pthread_t*) malloc(threads_num * sizeof(pthread_t));
  for (int i = 0; i < threads_num; i++){
    pthread_t my_thread = NULL;
    my_threads[i] = my_thread;
  }

  //making them start
  thread_param_t param = {matrix1, rows1, cols1, matrix2, cols2, matrix3, 0, 0};
  for (int i = 0; i < threads_num; i++){
    (&param)->row_end += rows_for_threads[i];
    pthread_create(&my_threads[i],NULL,mulRowsCols, &param);
    (&param)->row_start += rows_for_threads[i];
  }

  //waiting for all of them to finish
  for(int i = 0; i < threads_num; i++){
    pthread_join(my_threads[i], NULL);
  }

  return matrix3;
}

int main(int argc, char const *argv[])
{
  srand(time(0));
  int rowsA, colsA, rowsB, colsB, rowsC, colsC, threads_num;
  printf("\n\n🚀Rows of the first matrix : ");
  scanf("%d", &rowsA);
  printf("\n\n🚀Columns of the first matrix : ");
  scanf("%d", &colsA);
  
  //il numero di righe della matrice B deve essere uguale al numero di colonne della matrice A
  printf("\n\n🚀Columns of the second matrix : ");
  rowsB = colsA;
  scanf("%d", &colsB);

  //ricaviamo il numero di righe e di colonne per la matrice C
  rowsC = colsB;
  colsC = rowsA;

  //chiediamo con quanti thread deve essere calcolata la moltiplicazione
  printf("\n\n🚀Number of threads : ");
  scanf("%d", &threads_num);

  //creaiamo le matrici
  float **matrixA = createMatrix(rowsA,colsA);
  float **matrixB = createMatrix(rowsB,colsB);
  float **matrixC = createMatrix(rowsC,colsC);
  
  //calcoliamo con i threads
  double time_spent = 0.0;
  clock_t begin = clock();
  float **matrixAB = mulMatrices(matrixA, matrixB, rowsA, colsA, rowsB, colsB, threads_num);
  clock_t end = clock();
  time_spent += (double)(end - begin) / CLOCKS_PER_SEC;
 
  //calcoliamo senza i threads
  double time_spent2 = 0.0;
  float **matrixAB2 = createMatrix(rowsA,colsB);
  thread_param_t param = {matrixA, rowsA, colsA, matrixB, colsB, matrixAB2, 0, rowsA};
  clock_t begin2 = clock();
  mulRowsCols(&param);
  clock_t end2 = clock();
  time_spent2 += (double)(end2 - begin2) / CLOCKS_PER_SEC;

  //stampa dei risultati
  /*
  printf("\n\n✅ MATRIX A");
  printf("\n\n\n");
  printMatrix(matrixA, rowsA, colsA);
  printf("\n\n✅ MATRIX B");
  printf("\n\n\n");
  printMatrix(matrixB, rowsB, colsB);
  printf("\n\n\n");
  */  
  printf("\n\n✅ MATRIX AB");
  printf("\n\n\n");
  printMatrix(matrixAB, rowsA, colsB);
  
  printf("\n\n\n");
  printf("Matrices calulated with threads : %f\n\n", time_spent);
  printf("Matrices calulated without threads : %f\n\n", time_spent2);

  return 0;
}
