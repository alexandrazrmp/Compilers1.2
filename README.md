# Compilers1.2

A small two-stage compiler project built with **JFlex** and **Java CUP**.

The project parses a custom string-oriented language and translates it into Java code through an intermediate representation (IR).

## Project Structure

```text
Compilers1.2/
├── part2/
│   ├── Main.java
│   ├── Makefile
│   ├── scanner.flex
│   ├── parser.cup
│   ├── irscanner.flex
│   ├── irparser.cup
│   └── input.txt
├── java-cup-11b.jar
├── java-cup-11b-runtime.jar
└── JFlex - Java CUP.pdf
```

## Overview

The compiler works in **two stages**:

### Stage 1: Source Language → IR
- `scanner.flex` defines the lexer for the source language
- `parser.cup` defines the parser and translation rules
- Output is written to `Translated.ir`

### Stage 2: IR → Java
- `irscanner.flex` defines the lexer for the intermediate representation
- `irparser.cup` defines the parser that emits Java code
- Output is written to `Translated.java`

`Main.java` orchestrates both stages.

## Supported Language Features

The source language currently supports:

- string literals
- function declarations
- function calls
- parameters
- string concatenation with `+`
- `if ... else`
- `prefix`
- `suffix`
- `reverse`

### Example

```txt
name() { "John" }
repeat(x) { x + x }
cond_repeat(c, x) { if (x = c) repeat(name()) else x }

cond_repeat("yes", name())
cond_repeat("no?", "Jane")
```

## Build Requirements

Make sure you have installed:

- Java
- `javac`
- `make`
- JFlex

The project already includes the required Java CUP jars.

## How to Build

Move into the `part2` directory:

```bash
cd part2
make
```

This will:

- generate the scanners with JFlex
- generate the parsers with Java CUP
- compile all Java sources

## How to Run

```bash
make run
```

The program reads input from standard input.

After execution, it produces:

- `Translated.ir`
- `Translated.java`

## Clean Generated Files

```bash
make clean
```

This removes generated Java files, class files, and translation outputs.

## Notes

- The build uses Java CUP from the parent directory.
- If your environment is different, you may need to adjust the jar paths in the `Makefile`.
- `input.txt` can be used as a starting point for testing the language.

## Author

Alexandra Zormpa
