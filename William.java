import java.util.*;
import java.io.*;

public class William {
    public static void main(String[] args) throws FileNotFoundException {
        new William().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("william.dat"));

        TreeMap<String, String> tm = new TreeMap<>();

        while (f.hasNext()) {
            String first = f.next();
            String last = f.next();
            tm.put(last, first);
        }

        for (String last : tm.keySet()) {
            System.out.println(tm.get(last) + " " + last);
        }

        f.close();
    }
}
