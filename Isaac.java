import java.util.*;
import java.io.*;

public class Isaac {
    public static void main(String[] args) throws FileNotFoundException {
        new Isaac().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("Isaac.dat"));

        int t = f.nextInt();

        while (t-- > 0) {
            String[] files = new String[f.nextInt()];

            int max = 0;
            for (int i = 0; i < files.length; i++) {
                files[i] = f.next();
                max = Math.max(max, files[i].length());
            }
            Arrays.sort(files);
            int cols = 60/(max+2);
            System.out.println(cols);

            System.out.printf("%60s\n", "111111111122222222223333333333444444444455555555556");
            System.out.printf("%60s\n", "123456789012345678901234567890123456789012345678901234567890");
            System.out.println("------------------------------------------------------------");

            String queue = "";
            for (int i = 0; i < cols; i++) {
                if (queue.length() + (files[cols]+" ".repeat(60/cols - files[cols].length()+2)).length() > 60) {
                    System.out.println(queue);
                    queue = "";
                }

                queue += files[cols]+" ".repeat(60/cols - files[cols].length()+2);
            }
        }

        f.close();
    }
}
