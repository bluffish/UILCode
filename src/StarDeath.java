import java.util.*;
import java.io.*;

public class StarDeath {
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
        new StarDeath().run();
    }

    int SPACE = 0;
    int WALL = 1;
    int GENERATOR = 2;
    int TROOPER = 3;
    int VADER = 4;
    int EXPLOSIVE = 5;


    public void run () {
        IO f = new IO();

        int t = f.nextInt();

        out: while (t-- > 0) {
            int levels = f.nextInt();
            int rows = f.nextInt();
            int cols = f.nextInt();
            int explosion_time = f.nextInt();

            int[][][] m = new int[levels][rows][cols];

            for (int l = 0; l < levels; l++) {
                for (int r = 0; r < rows; r++) {
                    String s = f.next();
                    for (int c = 0; c < cols; c++) {
                        if (s.charAt(c) == '.') {
                            m[l][r][c] = SPACE;
                        } else if (s.charAt(c) == '#') {
                            m[l][r][c] = WALL;
                        } else if (s.charAt(c) == 'G') {
                            m[l][r][c] = GENERATOR;
                        } else if (s.charAt(c) == 'S') {
                            m[l][r][c] = TROOPER;
                        } else if (s.charAt(c) == 'D') {
                            m[l][r][c] = VADER;
                        } else if (s.charAt(c) == 'e') {
                            m[l][r][c] = EXPLOSIVE;
                        } else if (s.charAt(c) == 'g') {
                            System.out.println("Lmao grinched loser ");
                        }
                    }
                }
            }
        }

        f.close();
    }
}
