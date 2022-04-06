CFLAGS=-O0 -Wall -pedantic -Werror

main: main.o
	$(CC) $(CFLAGS) main.o -o main -lpthread

main.o: Laboratorio1.c
	gcc -c Laboratorio1.c -o main.o -Wall -Werror

clean:
	rm -f main main.o

.PHONY: clean