import java.util.*;
import java.io.*;

public class Megan {
    public static void main(String[] args) throws FileNotFoundException {
        new Megan().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("Megan.dat"));

        int t = f.nextInt();

        int tcase = 0;

        o: while (t-- > 0) {
            String alph = f.next();
            String[] words = f.next().split(",");

            for (int i = 0; i < words.length; i++) {
                String word = "";
                for (char c : words[i].toCharArray()) {
                    int pos = alph.indexOf(c);
                    word += (char)(97+pos);
                }
                words[i] = word;
            }


            for (int i = 1; i < words.length; i++) {
                if (words[i-1].compareTo(words[i]) > 0) {
                    System.out.println("no");
                    continue o;
                }
            }
            System.out.println("yes");
        }

        f.close();
    }
}
