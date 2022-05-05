import org.antlr.v4.runtime.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.io.FileWriter;

/**
 * this is the detailed description for this class
 * the class name is <p>AnalyzerDynamic</p> which use to override method from <p>JavaParserBaseListener </p>
 * to generate intermediate java file output
 * run this output file to generate txt output file contains all blocks which is visited
 */
public class AnalyzerDynamic extends JavaParserBaseListener {
    public static String out = "";
    public static TokenStreamRewriter rewriter;
   // public static TokenStreamRewriter rewriterhtml;
    public static String file_no="0";
   // public static int index_arr=0;
    public static  int block_no = 0; //start with first block
  //  public static ArrayList<Integer> branchCoverageArray= new ArrayList<Integer>(); // Create an ArrayList object

    /**
     * this is constructure of this class
     * generate tokenStream has tokens of input file
     * @param tokens
     * @throws IOException
     */
    public AnalyzerDynamic(TokenStream tokens) throws IOException { //constructor
        rewriter = new TokenStreamRewriter(tokens); //get input tokens from input file to save it in out file
       // rewriterhtml = new TokenStreamRewriter(tokens);
    }
/**
 * override <p>enterBlock</p> method from <p>JavaParserBaseListener</p>
 * this function will be executed when enter to new block '{'
 * @param ctx which contain '{'
 *  @throws IOException
 * this method return <p>void</p>
 * this method used to insert into rewrite token stream injection java code to modify user input code
 * and insert html code to highlight user code and coverage it
 * in this method call  <p>inject()</p> method
 * <p>inject()</p> method contain modification java code
 */
    public void enterBlock(JavaParser.BlockContext ctx) throws IOException {
        if (block_no == 0) {
            //to write in the first line in file
            //insertAfter insert after index
            rewriter.insertAfter(-1,"import java.io.FileWriter;\n"+
                    "import java.util.*;\n"
            +"import java.io.IOException;\n");

            //return index of current block (token { , } )
            int indexStart = ctx.getStart().getTokenIndex();
            String block = String.format("\"block no: \""); //to print string in System.out.println() in output file

            rewriter.insertAfter(indexStart, "\nSortedSet<Integer> visited = new TreeSet<Integer>();\n");

            int indexEnd = ctx.getStop().getTokenIndex();

            String line= String.format("\"  \"");
            String out= String.format("\"output_txt/outputTXT#%s.txt\"", file_no);

            rewriter.insertBefore(indexEnd , "\ntry {\n" +
                    "            FileWriter writer = new FileWriter("+out+", false);\n" +
                    "            writer.write(\" \");\n" +
                    "            writer.close();\n" +
                    "        } catch (IOException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }" +
                    "\ntry{\n" +
                    "FileWriter writer = new FileWriter("+out+", true);\n"+
                    "\nIterator<Integer> my_iterator = visited.iterator();\n" +
                    "            while (my_iterator.hasNext()){\n" +
                    "                writer.write(my_iterator.next().toString());\n"  +
                    "                writer.write("+line+");\n}"+
                    "            writer.close();\n}"
                    +
                    "         catch (IOException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n");

            //write html code
            //to write in the first line in file
            //insertAfter insert after index
/*
            rewriterhtml.insertAfter(-1, "<!DOCTYPE html>\n <html lang=\"en\">\n " +
                    "<head> \n<meta charset=\"UTF-8\">\n <title>Dynamic Analyzer</title>\n</head>" +
                    "\n<body> <div style=\"background-color:rgb(229, 255, 236); outline: 1px dotted transparent;\">\n");
            int htmlIndexEnd = ctx.getStop().getTokenIndex();
            rewriterhtml.insertBefore(htmlIndexEnd +2, "\n</div> \n</body>\n" + "</html>");

 */
        }

        int indexEnd = ctx.getStart().getTokenIndex();
       // rewriterhtml.insertAfter(indexEnd, "<br>\n");
        inject(indexEnd);
        //inject_html(indexEnd);
        block_no++;

    }
/*
    public void enterIfStatement(JavaParser.IfStatementContext ctx){
        int indexEnd = ctx.getStart().getTokenIndex();
        rewriterhtml.insertAfter(indexEnd, "<br>\n");
        rewriter.insertAfter(indexEnd,"{\n");
        inject(indexEnd);
        inject_html(indexEnd);
        block_no++;
    }
    public void exitIfStatement(JavaParser.IfStatementContext ctx){
        int indexEnd = ctx.getStop().getTokenIndex();
        rewriterhtml.insertBefore(indexEnd, "<br>\n");
        rewriter.insertAfter(indexEnd,"}\n");
    }

 */
    /**
     * override <p>enterStatement</p> method from <p>JavaParserBaseListener</p>
     * @param ctx
     * to handel single statement for control expression
     */
    @Override public void enterStatement(JavaParser.StatementContext ctx) {
        if (ctx.IF() != null || ctx.FOR() != null || ctx.WHILE() != null) {
            int StatementIndex = 0;
            if (!ctx.statement(StatementIndex).getStart().getText().equals("{")) {
                rewriter.insertBefore(ctx.statement(StatementIndex).getStart(), "{\n");
                rewriter.insertAfter(ctx.statement(StatementIndex).getStop(), "\nvisited.add(" + block_no + ");\n }\n");
                block_no++;
                System.err.println("Enter if");
            }
        }

        if (ctx.ELSE() != null) {
            int StatementIndex = 1;
            //else
            if (!ctx.statement(StatementIndex).getStart().getText().equals("if")) {
                if (!ctx.statement(StatementIndex).getStart().getText().equals("{")) {
                    rewriter.insertBefore(ctx.statement(StatementIndex).getStart(), "{\n");
                    rewriter.insertAfter(ctx.statement(StatementIndex).getStop(), "\nvisited.add(" + block_no + ");\n }\n");
                    block_no++;
                    System.err.println("Enter else");
                }
            }
        }
    }
    /**
     * inject() method to inject modification java code to generate intermediate java code
     * param index of the tokens to insert code after it
     * contain modification java code to generate output java file
     */
    public static void inject(int next)
    {
        //String block = String.format("\"#%d\"", block_no);

        rewriter.insertAfter(next, "\nvisited.add(" + block_no + ");\n" );
    }


}