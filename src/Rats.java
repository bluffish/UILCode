import java.util.*;
import java.io.*;

public class Rats {
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
        new Rats().run();
    }

    int OPEN = 0;
    int WALL = 1;
    int CHEESE = 2;

    public void run () {
        IO f = new IO();

        int t = f.nextInt();

        while (t-- > 0) {
            int size = f.nextInt();

            int[][] m = new int[size][size];
            int startr = -1;
            int startc = -1;

            for (int r = 0; r < size; r++) {
                String s = f.next();
                for (int c = 0; c < size; c++) {
                    if (s.charAt(c) == '.')
                        m[r][c] = OPEN;
                    else if (s.charAt(c) == '#')
                        m[r][c] = WALL;
                    else if (s.charAt(c) == 'C')
                        m[r][c] = CHEESE;
                    else {
                        m[r][c] = OPEN;
                        startr = r;
                        startc = c;
                    }

                }
            }

            System.out.println(go(startr, startc, m) ? "Cheese Found" : "Dead Rat");
        }

        f.close();
    }

    public boolean go (int r, int c, int[][] m) {
        if (m[r][c] == CHEESE)
            return true;
        if (m[r][c] == WALL)
            return false;
        m[r][c] = WALL;
        return go(r+1, c, m) ||
                go(r-1, c, m) ||
                go(r, c-1, m) ||
                go(r, c+1, m);
    }
}
