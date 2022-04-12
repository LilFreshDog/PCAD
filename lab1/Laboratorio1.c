/**
 * @file Laboratorio1.c
 * @author Milo Galli (4793560@studenti.unige.it)
 * @author Samuele Pignone (4838155@studenti.unige.it)
 * @author Giorgio Rengucci (4483986@studenti.unige.it)
 * @brief Matrix multiplication using pthread.
 *        Computes C[p][m] * A[m][n] * B[n][p]
 *        Usage "make all" -> "./main <m> <n> <p> <thread_number>" 
 *        Ouputs computation times
 * @version 1
 * @date 2022-04-10
 *
 * @copyright Copyright (c) 2022
 *
 */


#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
//#include <pthread.h>
#include "pthread_barrier.h"

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

pthread_barrier_t barrier;

float** createMatrix(int rows, int cols){
  //create the matrix
  float** matrix = (float**) malloc(rows * sizeof(float*));
  for(int i = 0;i<rows;i++) {
    matrix[i] = (float*) malloc(cols * sizeof(float));
  }

  //fill the matrix with random values
  for(int i = 0;i<rows;i++) {
    for(int j = 0;j<cols;j++) {
      matrix[i][j] = (float)rand()/(float)(RAND_MAX/10.0);
    }
  }

  return matrix;
}

float** createMatrixOfZeros(int rows, int cols){
  //create the matrix
  float** matrix = (float**) malloc(rows * sizeof(float*));
  for(int i = 0;i<rows;i++) {
    matrix[i] = (float*) malloc(cols * sizeof(float));
    memset(matrix[i], 0, cols*sizeof(float));
  }
  return matrix;
}

thread_param_t* createParameters(int* rows_for_threads, float** matrixA, int rowsA, int colsA, float** matrixB, int colsB, float** matrixAB, int threads_num ){
  thread_param_t * my_args = (thread_param_t*) malloc(threads_num * sizeof(thread_param_t));
  int starting_row = 0;
  int ending_row = 0;
  for (int i = 0; i < threads_num; i++){
    ending_row += rows_for_threads[i];
    thread_param_t my_arg = {matrixA, rowsA, colsA, matrixB, colsB, matrixAB, starting_row, ending_row};
    my_args[i] = my_arg;
    starting_row = ending_row;
  } 
  return my_args;
}

void printMatrix(float **matrix, int rows, int cols) {
  for (int i = 0; i < rows; i++) {
    printf("[ ");
    for (int j = 0; j < cols; j++) {
      j == cols - 1 ? printf("%f ]\n", matrix[i][j]) : printf("%f | ", matrix[i][j]);
    }
  }
  printf("\n");
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
  #ifdef DEBUG
  printf("\n\n%d è il thread che sta eseguendo la mulrowcols\n\n", (int)pthread_self());
  printf("%d --> %d\n\n", param->row_start, param->row_end);
  #endif
  
  float row_col_result = 0;
  //iterating on the rows of the first column
  for(int i = param->row_start; i < param->row_end; i++){
    //iterating over all the second matrix's columns
    for(int k = 0; k < param->colsB; k++ ){
      row_col_result = 0;
      //iterating over all the elements of the current row
      for (int j = 0; j < param->colsA; j++){
        row_col_result += param->matrixA[i][j] * param->matrixB[j][k]; 
      }
      param->matrixAB[i][k] = row_col_result;
    }
  }

  #ifdef DEBUG
  printf("%d è il thread che ha finito la mulrowcols\n\n", (int)pthread_self());
  #endif
  pthread_barrier_wait(&barrier);
  return NULL;
}

float** mulMatrices(float** matrixA, float** matrixB, float** matrixC, int rowsA, int colsA, int rowsB, int colsB, int rowsC, int colsC, int threads_num){  

  pthread_barrier_init(&barrier, NULL, threads_num);

  //creating the final matrix
  float** matrixABC = createMatrixOfZeros(rowsC,colsB);
  //creating the first product matrix
  float** matrixAB = createMatrixOfZeros(rowsA, colsB);

  //get the number of rows that every thread must handle
  int *rows_for_threads = rowsForThread(rowsA, threads_num);
  int * rows_for_threads2 = rowsForThread(rowsC, threads_num);

  //creating an array of threads
  pthread_t * my_threads = (pthread_t*) malloc(threads_num * sizeof(pthread_t));
  for (int i = 0; i < threads_num; i++){
    pthread_t my_thread = NULL;
    my_threads[i] = my_thread;
  }

  //creating an array of parameters for A*B
  thread_param_t * my_args = createParameters(rows_for_threads, matrixA, rowsA, colsA, matrixB, colsB, matrixAB, threads_num);
  // creating an array of parameters for C*AB
  thread_param_t * my_args2 = createParameters(rows_for_threads2, matrixC, rowsC,colsC, matrixAB, colsB, matrixABC, threads_num);
  //doing the multiplication
  for (int i = 0; i < threads_num; i++){
    pthread_create(&my_threads[i],NULL,mulRowsCols, &my_args[i]);
  }
  for (int i = 0; i < threads_num; i++){
    pthread_create(&my_threads[i],NULL,mulRowsCols, &my_args2[i]);
  }
	
  //waiting for all of them to finish
  for(int i = 0; i < threads_num; i++){
    pthread_join(my_threads[i], NULL);
  }
	//destroying the thread's barrier
  pthread_barrier_destroy(&barrier);
  
	return matrixABC;
}

int main(int argc, char const *argv[])
{
  srand(time(0));
  int rowsA, colsA, rowsB, colsB, rowsC, colsC, threads_num;

  if (argc == 5)
  {
#ifdef DEBUG
    printf("\n> Running in cli mode <\n");
    printf("  - M = %d\n", atoi(argv[1]));
    printf("  - N = %d\n", atoi(argv[2]));
    printf("  - P = %d\n", atoi(argv[3]));
    printf("  - Threads = %d\n\n", atoi(argv[4]));
#endif
    rowsA = atoi(argv[1]);
    colsA = atoi(argv[2]);
    colsB = atoi(argv[3]);
    threads_num = atoi(argv[4]);

    rowsB = colsA;
    rowsC = colsB;
    colsC = rowsA;
  } else if (argc == 1)
  {
    printf("you can also call the program with the following parameters:\n");
    printf("- M: rows of A and columns of C\n");
    printf("- N: columns of A and rows of B\n");
    printf("- P: columns of B and rows of C\n");
    printf("- T: number of threads\n");

    // richiedo dati input
    printf("\n🚀 Rows of the first matrix : ");
    scanf("%d", &rowsA);
    printf("🚀 Columns of the first matrix : ");
    scanf("%d", &colsA);
    
    //il numero di righe della matrice B deve essere uguale al numero di colonne della matrice A
    printf("🚀 Columns of the second matrix : ");
    rowsB = colsA;
    scanf("%d", &colsB);

    //ricaviamo il numero di righe e di colonne per la matrice C
    rowsC = colsB;
    colsC = rowsA;

    //chiediamo con quanti thread deve essere calcolata la moltiplicazione
    printf("\n🚀 Number of threads : ");
    scanf("%d", &threads_num);
  } else return 1;

  #ifdef DEBUG
  printf("Il thread del main è %d", (int)pthread_self());
  #endif

  //creaiamo le matrici
  float **matrixA = createMatrix(rowsA,colsA);
  float **matrixB = createMatrix(rowsB,colsB);
  float **matrixC = createMatrix(rowsC,colsC);
  
  //calcoliamo con i threads
  clock_t begin = clock();
  float **matrixABC = mulMatrices(matrixA, matrixB, matrixC, rowsA, colsA, rowsB, colsB, rowsC, colsC, threads_num);
  clock_t end = clock();
  double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;

  //calcoliamo senza i threads
  clock_t begin2 = clock();
  float **matrixABC_nothreads = mulMatrices(matrixA, matrixB, matrixC, rowsA, colsA, rowsB, colsB, rowsC, colsC, 1);
  clock_t end2 = clock();
  double time_spent2 = (double)(end2 - begin2) / CLOCKS_PER_SEC;

  //stampa dei risultati
  if(colsA<=10 && colsB<=10 && colsC<=10){
    printf("✅ MATRIX A\n");
    printMatrix(matrixA, rowsA, colsA);
    printf("✅ MATRIX B\n");
    printMatrix(matrixB, rowsB, colsB);
		printf("✅ MATRIX C\n");
    printMatrix(matrixC, rowsC, colsC);
    printf("✅ MATRIX ABC\n");
    printMatrix(matrixABC, rowsA, colsB);
  } else printf("ℹ️ The matrix is too big to be printed\n");
  
  printf("Matrices calulated with threads    : %f\n", time_spent);
  printf("Matrices calulated without threads : %f\n", time_spent2);

  return 0;
}
