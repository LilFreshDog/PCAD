main: lab matrix_util pthread_barrier
	$(CC) -o main Laboratorio1.o pthread_barrier.o matrix_util.o -lpthread -O0

debug: lab matrix_util pthread_barrier
	$(CC) -o debug Laboratorio1.o pthread_barrier.o matrix_util.o -lpthread -O0 -DDEBUG

lab: Laboratorio1.c
	$(CC) Laboratorio1.c -c

matrix_util: matrix_util.c
	$(CC) matrix_util.c -c

pthread_barrier: pthread_barrier.c
	$(CC) pthread_barrier.c -c

all: main debug

clean:
	rm -f main debug *.o

.PHONY: clean