.PHONY: build clean run check

build:
	javac -d out src/Tema1/*.java

run: build
	java -cp out Main $(ARGS)

check: build
	bash checker/checker.sh

clean:
	rm -rf out
