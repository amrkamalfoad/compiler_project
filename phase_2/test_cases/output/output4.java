import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
class WhileLoopExample {
    public static void main(String args[]){
ArrayList<Integer> visited = new ArrayList<Integer>(); // Create an ArrayList object

if (!visited.contains(1)) {
  
                visited.add(1);
            }        
        int i=10;
        while(i>1){
if (!visited.contains(2)) {
  
                visited.add(2);
            }        
            System.out.println(i);
            i--;
        }
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#4.txt", false);
            //BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#4.txt", true);

for(int l=0 ; l< visited.size(); l++){
System.out.print("block no: ");
System.out.println(visited.get(l));
            //BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write(visited.get(l).toString());
 writer.write("  ");
}            writer.close();
}         catch (IOException e) {
            e.printStackTrace();
        }

    }
}