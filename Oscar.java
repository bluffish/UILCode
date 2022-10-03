import java.util.*;
import java.io.*;

public class Oscar {
    public static void main(String[] args) throws FileNotFoundException {
        new Oscar().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("Oscar.dat"));

        int t = f.nextInt();
        f.nextLine();
        while (t-- > 0) {
            String[] inputs = f.nextLine().split(" ");

            System.out.print(inputs[0] + " ");
            int cur =Integer.parseInt(inputs[1]);

            for (int i = 2; i < inputs.length; i += 2) {
                String name = inputs[i];
                int size = Integer.parseInt(inputs[i+1]);

                if (size >= cur) {
                    System.out.print(name + " ");
                    cur = size;
                }
            }

            System.out.println();
        }

        f.close();
    }
}
