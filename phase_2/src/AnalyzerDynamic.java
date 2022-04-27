import org.antlr.v4.runtime.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.io.FileWriter;

public class AnalyzerDynamic extends JavaParserBaseListener {
    public static String out = "";
    public static TokenStreamRewriter rewriter;
    public static String file_no="0";
    public AnalyzerDynamic(TokenStream tokens) { //constructor
        rewriter = new TokenStreamRewriter(tokens); //get input tokens from input file to save it in out file

    }

    int block_no = 0; //start with first block

    // override enterBlock method from JavaParserBaseListener
    public void enterBlock(JavaParser.BlockContext ctx) throws IOException {
        if (block_no == 0) {
            //to write in the first line in file
            //insertAfter insert after index
            rewriter.insertAfter(-1, "import java.util.ArrayList;\nimport java.io.FileWriter;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.*;\n");

            //return index of current block (token { , } )
            int indexStart = ctx.getStart().getTokenIndex();
            String block = String.format("\"block no: \""); //to print string in System.out.println() in output file

            rewriter.insertAfter(indexStart, "\nArrayList<Integer> visited = new ArrayList<Integer>(); // Create an ArrayList object\n");

            int indexEnd = ctx.getStop().getTokenIndex();

            String line= String.format("\"  \"");
            String out= String.format("\"output_txt/outputTXT#%s.txt\"", file_no);

            rewriter.insertAfter(indexEnd - 2, "\ntry {\n" +
                    "            FileWriter writer = new FileWriter("+out+", false);\n" +
                    "            //BufferedWriter bufferedWriter = new BufferedWriter(writer);\n" +
                    "            writer.write(\"\");\n" +
                    "            writer.close();\n" +
                    "        } catch (IOException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }" +

                    "\ntry{\n" +
                    "FileWriter writer = new FileWriter("+out+", true);\n"+
                    "\nfor(int l=0 ; l< visited.size(); l++){\nSystem.out.print(" + block + ");\nSystem.out.println(visited.get(l));\n"
            +
                    "            //BufferedWriter bufferedWriter = new BufferedWriter(writer);\n" +
                    "            writer.write(visited.get(l).toString());\n"  +
                    " writer.write("+line+");\n}"+
                    "            writer.close();\n}"
            +
                    "         catch (IOException e) {\n" +
                            "            e.printStackTrace();\n" +
                            "        }\n");
        }

        block_no++;
        // get the index of the last token in the parseTree
        int next = ctx.getStart().getTokenIndex();

        //String block = String.format("\"#%d\"", block_no);

        rewriter.insertAfter(next, "\nif (!visited.contains(" + block_no + ")) {\n" +
                "  \n" +
                "                visited.add(" + block_no + ");\n" +
                "            }        ");
        //visited.add(" +  block_no  + ");
        /*
        out=Integer.toString(block_no);
        try {
            FileWriter writer = new FileWriter("output/output.txt", true);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(out);
            bufferedWriter.write('\n');
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txt(Integer.toString(block_no));
    }

    public void enterStatement(JavaParser.StatementContext ctx) {

    }
        static void txt (String s)
        {
            out += s + " ";
        }

         */
    }
}