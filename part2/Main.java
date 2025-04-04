import java_cup.runtime.*;
import java.io.*;


class Main {
    public static void main(String[] argv) throws Exception {
        System.err.println("Please give your input:");

        // === Stage 1: Parse input and generate IR ===
        Reader inputReader = new InputStreamReader(System.in);
        Scanner scanner = new Scanner(inputReader);
        Parser parser = new Parser(scanner);

        // Save original System.out to restore later
        PrintStream originalOut = System.out;

        // Redirect IR output to Translated.ir
        PrintStream irOut = new PrintStream(new FileOutputStream("Translated.ir"));
        System.setOut(irOut);
        parser.parse();
        irOut.close();
        System.setOut(originalOut); // Restore standard output

        System.err.println("Stage 1 complete: IR written to Translated.ir");
        System.err.println("Generating Java code from IR...");

        // === Stage 2: Parse IR and generate Java code ===
        Reader irReader = new FileReader("Translated.ir");
        irScanner irScanner = new irScanner(irReader);   // Your IR scanner
        irParser irParser = new irParser(irScanner);

        // Redirect Java output to Translated.java
        PrintStream javaOut = new PrintStream(new FileOutputStream("Translated.java"));
        System.setOut(javaOut);
        irParser.parse();
        javaOut.close();
        System.setOut(originalOut); // Restore again

        System.err.println("Translation completed. Check Translated.java.");
    }
}
