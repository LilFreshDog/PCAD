main: Laboratorio1.c
	$(CC) Laboratorio1.c -o main -lpthread

debug: Laboratorio1.c
	$(CC) Laboratorio1.c -o debug -lpthread -DDEBUG

all: main debug

clean:
	rm -f main debug main.o

.PHONY: clean