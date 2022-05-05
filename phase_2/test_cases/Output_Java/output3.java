import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class DoWhileExample {
    public static void main(String[] args) {
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);

        int i=1;
        do{
visited.add(1);

            System.out.println(i);
            i++;
        }while(i<=10);
    
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#3.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#3.txt", true);

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