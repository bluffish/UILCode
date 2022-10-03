import java.util.*;
import java.io.*;

public class Eagles {
    public static void main(String[] args) throws FileNotFoundException {
        new Eagles().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("Eagles.dat"));
//        Scanner f = new Scanner(System.in);

        int t = f.nextInt();

        while (t-- > 0) {
            int rows = 10;
            int cols = 10;
            int[][] m = new int[rows][cols];

            int startc = f.nextInt()-1;
            int startr = f.nextInt()-1;

            for (int i = 0; i < rows; i++) {
                String s = f.next();
                for (int j = 0; j < cols; j++) {
                    if (s.charAt(j) == 'J') {
                        startr = i;
                        startc = j;
                    } else if (s.charAt(j) == 'E') {
                        m[i][j] = 2;
                    } else if (s.charAt(j) == 'B' || s.charAt(j) == 'M') {
                        m[i][j] = 1;
                    }
                }
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(startr);
            q.add(startc);
            q.add(0);
            int[] vr = {1, 0, -1, 0, 1, 1, -1, -1};
            int[] vc = {0, 1, 0, -1, 1, -1, 1, -1,};
            while (!q.isEmpty()) {
                int r = q.poll();
                int c = q.poll();
                int time = q.poll();

                if (r < 0 || r >= 10 || c < 0 || c >= 10) {
                    continue;
                }

                if (m[r][c] == 2) {
                    System.out.println(time);
                    break;
                }
                if (m[r][c] == 1) {
                    continue;
                }

                for (int i = 0; i < 8; i++) {
                    int nr = r +vr[i];
                    int nc = c +vc[i];

                    q.add(nr);
                    q.add(nc);
                    q.add(time+1);
                }

            }
        }

        f.close();
    }
}
