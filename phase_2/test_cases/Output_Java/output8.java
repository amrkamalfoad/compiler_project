import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
class IFExample {
    public static void main(String[] args) {
SortedSet<Integer> visited = new TreeSet<Integer>();

visited.add(0);

        int i=1;
        int x=10;
        do{
visited.add(1);

            System.out.println(i);
            i++;
            if(x>20)
                {
System.out.println(x);
visited.add(2);
 }

            else
                {
System.out.println(x+5);
visited.add(3);
 }

        }while(i<=10);

        for(int j=0;j<4;j++)
        {
System.out.println(j);
visited.add(4);
 }


        while(x>20)
            {
System.out.println(x);
visited.add(5);
 }

    
try {
            FileWriter writer = new FileWriter("output_txt/outputTXT#8.txt", false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
try{
FileWriter writer = new FileWriter("output_txt/outputTXT#8.txt", true);

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