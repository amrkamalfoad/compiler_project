import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class WhileLoopExample {
    public static void main(String args[]){
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);

        int i=10;
        while(i>1){
visited.add(1);

            System.out.println(i);
            i--;
            for(int j=0;j>10;j++)
            {
visited.add(2);

                System.out.println(j);
            }
        }
    
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#4.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#4.txt", true);

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