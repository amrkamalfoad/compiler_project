import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
class Main5 {
    public static void main(String[] args) {
ArrayList<Integer> visited = new ArrayList<Integer>(); // Create an ArrayList object

if (!visited.contains(1)) {
  
                visited.add(1);
            }        

        int weeks = 3;
        int days = 7;

        // outer loop prints weeks
        for (int i = 1; i <= weeks; ++i) {
if (!visited.contains(2)) {
  
                visited.add(2);
            }        
            System.out.println("Week: " + i);

            // inner loop prints days
            for (int j = 1; j <= days; ++j) {
if (!visited.contains(3)) {
  
                visited.add(3);
            }        
                System.out.println("  Day: " + j);
            }
        }
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#5.txt", false);
            //BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#5.txt", true);

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