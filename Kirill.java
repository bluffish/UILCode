import java.util.*;
import java.io.*;

public class Kirill {
    public static void main(String[] args) throws FileNotFoundException {
        new Kirill().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("kirill_j.dat"));
        int t = f.nextInt();

        while (t-- > 0) {
            int n = f.nextInt();

            while (n > 0) {
                if (is_self(n)) break;
                n--;
            }

            System.out.println(n);
        }

        f.close();
    }

    public boolean is_self(int n) {
        for (int i = 0; i < n; i++) {
            if (sum(i) == n) {
                return false;
            }
        }
        return true;
    }

    public int sum (int n) {
        int total = n;
        while (n > 0) {
            total += n%10;
            n /= 10;
        }
        return total;
    }
}
