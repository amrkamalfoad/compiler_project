import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class Main{
        public static void main(String[]args){
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);


        int weeks=5;
        int days=7;

        // outer loop prints weeks
        for(int i=1;i<=weeks;++i){
visited.add(1);


        System.out.println("Week: "+i);

        // inner loop prints days
        for(int j=1;j<=days;++j){
visited.add(2);


                if(weeks==3)
                {
visited.add(3);


                        System.out.println("  Day: "+j);System.out.println("  Day: "+j);
                }
                else {
visited.add(4);


                        System.out.println("  Day: " + j);
                }
        }
        }
        
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#1.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#1.txt", true);

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

