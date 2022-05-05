import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class test {
    public static void main(String args[]) {
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);

        int x = 30;
        int y = 10;

        if( x == 30 ) {
visited.add(1);

            if( y == 20 ) {
visited.add(2);

                System.out.print("X = 30 and Y = 10");
            }
        }
        if(y==10)
        {
visited.add(3);

            if(x==10)
            {
visited.add(4);

                System.out.print("X = 10 ");
            }

            else {
visited.add(5);

                System.out.print("X small than 10");
            }
        }
        else{
visited.add(6);

            System.out.print("none");
        }
    
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#6.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#6.txt", true);

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