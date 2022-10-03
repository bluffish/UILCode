import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Fatimah {
    public static void main(String[] args) throws FileNotFoundException {
        new Fatimah().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("fatimah_j.dat"));

        int t = f.nextInt();

        while (t-- > 0) {
            int n = f.nextInt();
            int g = f.nextInt();

            String[] mons = new String[n];
            HashMap<String, HashSet<String>> hm = new HashMap<>();
            for (int i = 0; i < n; i++) {
                mons[i] = f.next();
                hm.put(mons[i], new HashSet<>());
            }

            for (int i = 0; i < g; i++) {
                String a = f.next();
                String b = f.next();

                hm.get(a).add(b);
                hm.get(b).add(a);
            }


            int max = 1;

            out: for (int i = 0; i < (1<<n); i++) {
                ArrayList<String> al = new ArrayList<>();
                for (int j = 0; j < n; j++)
                    if ((i & (1 << j)) > 0)
                        al.add(mons[j]);

                for (String a : al) {
                    for (String b : al) {
                        if (hm.get(a).contains(b))
                            continue out;
                    }
                }

                max = Math.max(al.size(), max);
            }


            System.out.println(max);
        }

        f.close();
    }
}
