import org.antlr.v4.runtime.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.io.FileWriter;

/**
 * this is the detailed description for this class
 * the class name is <p>Analyzerhtml</p> which use to override method from <p>JavaParserBaseListener </p>
 * use the output from intermediate java file output
 * by using this output txt file will generate html file
 * with <p> green</p> lines for lines will be visited
 * and <p>red</p> lines for lines will be never visited
 */

public class Analyzerhtml extends JavaParserBaseListener {
    public static TokenStreamRewriter rewriterhtml;
    public static String file_no="0";
    public static int index_arr=0;
    public static  int block_no = 0; //start with first block
    public static ArrayList<Integer> branchCoverageArray= new ArrayList<Integer>(); // Create an ArrayList object

    /**
     * this is constructure of this class
     * generate tokenStream rewriterhtml has tokens of input file
     * @param tokens
     * @throws IOException
     */
    public Analyzerhtml(TokenStream tokens) throws IOException { //constructor

        rewriterhtml = new TokenStreamRewriter(tokens);
    }
    /**
     * override <p>enterBlock</p> method from <p>JavaParserBaseListener</p>
     * this function will be executed when enter to new block '{'
     * @param ctx which contain '{'
     * @throws IOException
     * this method return <p>void</p>
     * insert html code to highlight user code and coverage it
     * in this method call  <p>inject_html</p> method
     * and <p>inject_html()</p> method contain modification html code
     */
    public void enterBlock(JavaParser.BlockContext ctx) throws IOException {

        if(block_no==0){
        rewriterhtml.insertAfter(-1, "<!DOCTYPE html>\n <html lang=\"en\">\n " +
                "<head> \n<meta charset=\"UTF-8\">\n <title>Dynamic Analyzer</title>\n</head>" +
                "\n<body> <div style=\"background-color:rgb(229, 255, 236); outline: 1px dotted transparent;\">\n");
        int htmlIndexEnd = ctx.getStop().getTokenIndex();
        rewriterhtml.insertBefore(htmlIndexEnd +2, "\n</div> \n</body>\n" + "</html>");
    }
        int indexEnd = ctx.getStart().getTokenIndex();
        //rewriterhtml.insertAfter(indexEnd, "<br>\n");
        inject_html(indexEnd);
        block_no++;
    }
    /**
     * override <p>exitBlock</p> method from <p>JavaParserBaseListener</p>
     * insert </div> every time exit from any block in html file
     */
    public void exitBlock(JavaParser.BlockContext ctx) {
        int indexEnd = ctx.getStop().getTokenIndex();
        // rewriterhtml.insertAfter(indexEnd, "<br>\n");
        rewriterhtml.insertBefore(indexEnd , "</div>\n");

    }

    /**
     * override <p>exitBlockStatement</p> method from <p>JavaParserBaseListener</p>
     * to insert newline <br> each time exit blockStatement in html file
     */

    @Override public void exitBlockStatement(JavaParser.BlockStatementContext ctx) {
        int indexx=ctx.getStart().getTokenIndex();
        rewriterhtml.insertBefore(indexx, "<br>\n");
        // rewriterhtml.insertBefore(indexx, "<br>\n");
    }

    /**
     * override <p>enterStatement</p> method from <p>JavaParserBaseListener</p>
     * @param ctx
     * to handel single statement for control expression
     */
    @Override public void enterStatement(JavaParser.StatementContext ctx) {
        int indexx=ctx.getStart().getTokenIndex();
        rewriterhtml.insertBefore(indexx,"<br>");
        if (ctx.IF() != null || ctx.FOR() != null || ctx.WHILE() != null) {
            int StatementIndex = 0;
            if (! ctx.statement(StatementIndex).getStart().getText().equals("{")) {
                //rewriter.insertBefore(ctx.statement(StatementIndex).getStart(), "{\n");
                //rewriter.insertAfter(ctx.statement(StatementIndex).getStop(), "\nvisited.add(" + block_no + ");\n }\n");
                //rewriterhtml.insertAfter(htmlIndex, "<br>\n");
                if ((index_arr<branchCoverageArray.size())&& (branchCoverageArray.get(index_arr)== block_no)) {

                    rewriterhtml.insertBefore(indexx, "\n<div style=\"background-color:rgb(229, 255, 236);" +
                            "outline: 1px dotted transparent; \">\n");
                    rewriterhtml.insertAfter(ctx.statement(StatementIndex).getStop(), "<br></div>");
                    index_arr ++;
                    //System.out.println("green");
                }
                else {
                    //int indexEnd = ctx.getStart().getTokenIndex();
                    rewriterhtml.insertBefore(ctx.statement(StatementIndex).getStart(), "\n<div style=\"background-color:rgb(255, 240, 240);" +
                            " outline: 1px dotted transparent; \">\n");
                    rewriterhtml.insertAfter(ctx.statement(StatementIndex).getStop(), "<br></div>");
                    // System.out.println("red");
                }

                block_no++;
                System.err.println("Enter if");
            }
        }

        if (ctx.ELSE() != null) {
            int StatementIndex = 1;
            if (!ctx.statement(StatementIndex).getStart().getText().equals("if")) {
                if (!ctx.statement(StatementIndex).getStart().getText().equals("{")) {
                    if ((index_arr < branchCoverageArray.size()) && (branchCoverageArray.get(index_arr) == block_no)) {

                        rewriterhtml.insertBefore(indexx, "\n<div style=\"background-color:rgb(229, 255, 236);" +
                                "outline: 1px dotted transparent; \">\n");
                        rewriterhtml.insertAfter(ctx.statement(StatementIndex).getStop(), "<br></div>");
                        index_arr++;
                        //System.out.println("green");
                    } else {
                        //int indexEnd = ctx.getStart().getTokenIndex(); rgb(255, 238, 240)
                        rewriterhtml.insertBefore(ctx.statement(StatementIndex).getStart(), "\n<div style=\"background-color:rgb(255, 240, 240);" +
                                " outline: 1px dotted transparent; \">\n");
                        rewriterhtml.insertAfter(ctx.statement(StatementIndex).getStop(), "<br></div>");
                        // System.out.println("red");
                    }

                    block_no++;
                    System.err.println("Enter else");
                }
            }
        }
    }

    /**
     * getBranchCoverageArray() method to save my visited blocks number
     * generate arraylist of visited blocks
     */
    public static void getBranchCoverageArray() throws IOException {

        BufferedReader input = new BufferedReader(new FileReader("output_txt\\outputTXT#"+file_no+".txt"));
        String last = " ", line;

        while ((line = input.readLine()) != null) {
            last = line;
        }

        System.out.println(last);
        // branchCoverageArray.add(0);
        int Index = 0 ;
        while(!last.isEmpty() && Index < last.length()) {
            if (last.charAt(Index) != ' ') {
                branchCoverageArray.add(Integer.parseInt(String.valueOf(last.charAt(Index))));
            }
            Index ++;
        }
        for (int i=0;i<branchCoverageArray.size();i++)
        {
            System.out.println(branchCoverageArray.get(i));
        }
    }
    /**
     * inject_html() method to inject html code to generate html file
     * \param index of the tokens to insert code after it
     * contain modification html code to generate output html file
     */
    public  static  void inject_html(int htmlIndex)
    {
        //rewriterhtml.insertAfter(htmlIndex, "<br>\n");
        if ((index_arr<branchCoverageArray.size())&& (branchCoverageArray.get(index_arr)== block_no)) {

            rewriterhtml.insertAfter(htmlIndex, "\n<div style=\"background-color:rgb(229, 255, 236);" +
                    "outline: 1px dotted transparent; \">\n");
            index_arr ++;
            //System.out.println("green");
        }
        else {
            //int indexEnd = ctx.getStart().getTokenIndex();
            rewriterhtml.insertAfter(htmlIndex, "\n<div style=\"background-color:rgb(255, 238, 240);" +
                    " outline: 1px dotted transparent; \">\n");
            // System.out.println("red");
        }
        // System.out.println(block_no);
    }
    /**
     * writehtml() method to write html code in a html file
     */
    public static void writehtml() throws IOException {
        FileWriter final_out_html = new FileWriter("outputHTML2/outputHtml"+file_no+".html");
        final_out_html.write(rewriterhtml.getText()); // rewrite input fiile and our modification in output file
        final_out_html.close();
    }
}
