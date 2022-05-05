import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class simpleprogram{
    public static void main(String[]args){
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);

        int x=5;
        int y=10;
        if(x<20) {
System.out.println(x);
visited.add(1);
 }

        else {
System.out.println(x+y);
visited.add(2);
 }

    
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#7.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#7.txt", true);

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