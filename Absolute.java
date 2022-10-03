import java.util.*;
import java.io.*;

public class Absolute {
    public static void main(String[] args) throws FileNotFoundException {
        new Absolute().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("absolute.dat"));

        int t = f.nextInt();
        while (t-- > 0) {
            int count = 0;

            int[] a = new int[4];
            for (int i = 0; i < 4; i++) {
                a[i] = f.nextInt();
            }

            while (check(a)) {
                int start = a[0];
                for (int i = 0; i < 3; i++) {
                    a[i] = Math.abs(a[i]-a[i+1]);
                }
                a[3] = Math.abs(a[3]-start);
                count++;

            }
            System.out.println(count + " " + a[0]);

        }

        f.close();
    }

    public boolean check (int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i+1]) return true;
        }
        return false;
    }
}
