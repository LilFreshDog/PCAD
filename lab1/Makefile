main: main.o
	gcc main.o -o main -lpthread

main.o: Laboratorio1.c
	gcc -c Laboratorio1.c -o main.o

clean:
	rm -f main main.o

.PHONY: clean