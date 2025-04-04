JAVAC=javac
JFLEX=jflex
CUP=java -jar ../java-cup-11b.jar  # Adjust this path to your CUP jar
CUP_RUNTIME=../java-cup-11b-runtime.jar  # Adjust runtime path if necessary

# Source files for Stage 1 (Pass-through IR)
SCANNER_SRC=scanner.flex
PARSER_SRC=parser.cup
SCANNER_OUT=Scanner.java
PARSER_OUT=Parser.java sym.java

# Source files for Stage 2 (IR → Java)
IR_SCANNER_SRC=irscanner.flex
IR_PARSER_SRC=irparser.cup
IR_SCANNER_OUT=irScanner.java
IR_PARSER_OUT=irParser.java irSym.java

# Java files
JAVA_SRC=Main.java

# Output files
IR_FILE=Translated.ir
JAVA_OUTPUT=Translated.java

# Default target
all: compiler

# Generate scanner for Stage 1
$(SCANNER_OUT): $(SCANNER_SRC)
	$(JFLEX) $(SCANNER_SRC)

# Generate parser for Stage 1
$(PARSER_OUT): $(PARSER_SRC)
	$(CUP) -interface -parser Parser $(PARSER_SRC)

# Generate scanner for Stage 2 (IR processing)
$(IR_SCANNER_OUT): $(IR_SCANNER_SRC)
	$(JFLEX) $(IR_SCANNER_SRC)

# Generate parser for Stage 2 (IR processing)
$(IR_PARSER_OUT): $(IR_PARSER_SRC)
	$(CUP) -interface -parser irParser $(IR_PARSER_SRC)

# Compile all Java files
compiler: $(SCANNER_OUT) $(PARSER_OUT) $(IR_SCANNER_OUT) $(IR_PARSER_OUT)
	$(JAVAC) -cp $(CUP_RUNTIME) *.java

# Run the compiler
run: compiler
	java -cp .:$(CUP_RUNTIME) Main

# Clean generated and compiled files
clean:
	rm -f $(SCANNER_OUT) $(PARSER_OUT) $(IR_SCANNER_OUT) $(IR_PARSER_OUT) *.class $(IR_FILE) $(JAVA_OUTPUT)
