import java.util.*;
import java.io.*;

public class MatrixDiagonalSums {
    public class IO extends PrintWriter {
        private BufferedReader r;
        private String line, token;
        private StringTokenizer st;

        public IO () {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(System.in));
        }

        public IO (String name) throws IOException {
            super(name+".out");
            r = new BufferedReader(new FileReader(name+".in"));
        }

        public boolean hasNext() { return peekToken() != null; }
        public int nextInt() { return Integer.parseInt(nextToken()); }
        public double nextDouble() { return Double.parseDouble(nextToken()); }
        public long nextLong() { return Long.parseLong(nextToken()); }
        public String next() { return nextToken(); }
        String nextLine() {
            String str = "";
            try {
                str = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
        public void println (Object... args) {
            for (Object a : args) {
                print(a);
            }
            print('\n');
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null)
                            return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                }
                catch (IOException e) {}
            return token;
        }
    }

    public static void main(String[] args) {
        new MatrixDiagonalSums().run();
    }

    public void run () {
        IO f = new IO();

        int[][] mat = {
                {5, 7, 3, 16},
                {6, 10, 21, 12},
                {8, 4, 20, 15},
                {2, 9, 11, 14},
        };

        while (true) {
            int n = f.nextInt();
            if (n == 0) break;

            int sum = 0;
            int r = 0;
            int c = 0;

            out: for (r = 0; r < mat.length; r++) {
                for (c = 0; c < mat[r].length; c++) {
                    if (mat[r][c] == n) break out;
                }
            }

            for (int i = 0; ; i++) {
                if (r+i >= 4 || c+i >= 4)
                    break;
                sum += mat[r+i][c+i];
            }

            for (int i = 0; ; i++) {
                if (r-i < 0 || c+i >= 4)
                    break;
                sum += mat[r-i][c+i];
            }

            for (int i = 0; ; i++) {
                if (r+i >= 4 || c-i < 0)
                    break;
                sum += mat[r+i][c-i];
            }

            for (int i = 0; ; i++) {
                if (r-i < 0 || c-i < 0)
                    break;
                sum += mat[r-i][c-i];
            }

            sum -= n*3;

            System.out.println(sum);
        }

        f.close();
    }
}
