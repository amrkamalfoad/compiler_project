import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.*;
import java.util.*;
public class AnalyzerDynamicTest {
    public static TokenStreamRewriter rewriter;
    public static CommonTokenStream tokens;
    public static ParseTree tree;
    Process theProcess;
    public static String file_name;
    public static FileInputStream file;
    public static void main(String [] args) throws Exception {
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter input file name \n");
        file_name = scanner.nextLine(); //take string as a input
        file = new FileInputStream("D:\\lectures\\lecture4-2\\Compiler\\phase2\\input\\input"+file_name+".java");
        //from CharStream class call fromFileName function in antlr package
        // take a file path as an input and return contain of this file.
        //CharStream input = CharStreams.fromFileName("D:\\lectures\\lecture4-2\\Compiler\\phase2\\input\\input"+file_name+".java");

        ANTLRInputStream input= new ANTLRInputStream(file);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        AnalyzerDynamic Analyzer = new AnalyzerDynamic(tokens); //java override method class
        Analyzer.file_no=file_name;
        parseTreeWalker.walk(Analyzer, tree);
        //Analyzer.file_no=file_name;


        FileWriter final_out = new FileWriter("output/output"+file_name+".java");
        final_out.write(Analyzer.rewriter.getText()); // rewrite input fiile and our modification in output file
        final_out.close();
        String path="output/output1";

        try{
            Runtime.getRuntime().exec("java "+ path);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        Process pro1 = Runtime.getRuntime().exec("java output/output1.java");
        pro1.waitFor();
        Process pro2 = Runtime.getRuntime().exec("java main");

        BufferedReader in = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
        String line = null;

        while ((line = in.readLine()) != null) {
            System.out.println(line);
            System.out.println("finished");
        }

        //System.out.println("finished");

        Process p = Runtime.getRuntime().exec(
                "\"D:\\lectures\\lecture4-2\\Compiler\\phase2\\output\\output1.java\"");
        p.waitFor();


        java -cp antlr-3.3-complete.jar org.antlr.Tool *.g
        javac -cp antlr-3.3-complete.jar *.java
        java -cp .:antlr-3.3-complete.jar output//output1


         */
        /*
       try {
            System.out.println("Opening notepad");
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec("output/output1.java");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Closing notepad");
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */


        //Runtime.getRuntime().exec("output/output.java");

    }
}
