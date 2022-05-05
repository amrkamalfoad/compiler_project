import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.*;
import java.util.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.concurrent.TimeUnit;
public class AnalyzerDynamicTest {
    /**
     * this is the detailed description for this class
     * the class name is <p>AnalyzerDynamicTest</p> which use to test our dynamic analyzer
     * \param take a input java file name
     * generate a output java file (intermediate) java code
     * run this output file to generate txt output file contains all blocks which is visited
     * by using this output txt file to generate html file
     * with <p> green</p> lines for lines will be visited
     * and <p>red</p> lines for lines will be never visited
     */
    //public static TokenStreamRewriter rewriter;
    //public static TokenStreamRewriter rewriterhtml;
    public static CommonTokenStream tokens;
    public static ParseTree tree;
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
        ParseTreeWalker parseTreeWalkerhtml = new ParseTreeWalker();
        AnalyzerDynamic Analyzer = new AnalyzerDynamic(tokens); //java override method class
        Analyzer.file_no=file_name;
        Analyzerhtml Analyzerhtml = new Analyzerhtml(tokens); //java override method class
        Analyzerhtml.file_no=file_name;
        parseTreeWalker.walk(Analyzer, tree);


        for(int i=0 ; i< tokens.size(); i++) {
            //Analyzer.rewriterhtml.insertAfter(i, " ");
            String tokenText2 = tokens.get(i).getText();
            if (tokenText2.equals("{") || tokenText2.equals("}")  ){
                Analyzerhtml.rewriterhtml.insertAfter(i, "<br>\n");
            }
        }



        File out1=new File("output/output"+file_name+".java") ;
        FileWriter final_out = new FileWriter("output/output"+file_name+".java");
        final_out.write(Analyzer.rewriter.getText()); // rewrite input fiile and our modification in output file
        final_out.close();

        // Compile source file.
        Process theProcess = null;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, out1.getPath());
        try{
            theProcess = Runtime.getRuntime().exec("java output/"+out1.getName());
            System.err.println("running done");
            TimeUnit.SECONDS.sleep(1);
        }
        catch(IOException e){
            System.err.println("Error on exec() method");
            e.printStackTrace();
        }


        Analyzerhtml.getBranchCoverageArray();
        parseTreeWalkerhtml.walk(Analyzerhtml, tree);
        Analyzerhtml.writehtml();

    }
}
