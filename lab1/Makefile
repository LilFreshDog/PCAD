main: Laboratorio1.c
	$(CC) Laboratorio1.c -o main -lpthread -O0

debug: Laboratorio1.c
	$(CC) Laboratorio1.c -o debug -lpthread -DDEBUG -O0

all: main debug

clean:
	rm -f main debug main.o

.PHONY: clean