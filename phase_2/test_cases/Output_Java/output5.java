import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class Main5 {
    public static void main(String[] args) {
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);


        int weeks = 3;
        int days = 7;

        // outer loop prints weeks
        for (int i = 1; i <= weeks; ++i) {
visited.add(1);

            System.out.println("Week: " + i);

            // inner loop prints days
            for (int j = 1; j <= days; ++j) {
visited.add(2);

                System.out.println("  Day: " + j);
            }
        }
    
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#5.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#5.txt", true);

Iterator<Integer> my_iterator = visited.iterator();
            while (my_iterator.hasNext()){
                writer.write(my_iterator.next().toString());
                writer.write("  ");
}            writer.close();
}         catch (IOException e) {
            e.printStackTrace();
        }
}
}