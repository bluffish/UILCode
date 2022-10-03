
import java.util.*;
import java.io.*;

public class Rebecca {
    public static void main(String[] args) throws FileNotFoundException {
        new Rebecca().run();
    }

    public void run () throws FileNotFoundException {
        Scanner f = new Scanner(new File("rebecca_j.dat"));

        int t = f.nextInt();

        while (t-- > 0) {
            int rows = f.nextInt();
            int cols = f.nextInt();

            int[][] m = new int[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    m[r][c] = f.nextInt();
                }
            }

            int tot = 0;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {

                    for (int r_ = r; r_ < rows; r_++) {
                        out: for (int c_ = c; c_ < cols; c_++) {
                            if (m[r_][c_] != m[r][c]) break;
                            int x1 = r;
                            int y1 = c;
                            int x2 = r_;
                            int y2 = c_;

                            for (int i = x1; i <= x2; i++) {
                                for (int j = y1; j <= y2; j++) {
                                    if (m[i][j] != m[x1][y1]) {
                                        continue out;
                                    }
                                }
                            }

                            tot++;
                        }
                    }

                }
            }

            System.out.println(tot);
        }


        f.close();
    }
}
