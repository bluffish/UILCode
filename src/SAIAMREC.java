import java.util.*;
import java.io.*;

public class SAIAMREC {
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
        new SAIAMREC().run();
    }

    public void run () {
        IO f = new IO();

        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ*********";

        while (true) {
            int size = f.nextInt();
            if (size == 0) break;

            char[][] mat = new char[(int) Math.ceil(Math.sqrt(size))][(int) Math.ceil(Math.sqrt(size))];
            go(mat.length-1, 0, 0, 1, mat, s, 0);
            show(mat);
        }

        f.close();
    }

    public void go (int r, int c, int rv, int cv, char[][] mat, String s, int pos) {
        if (r < 0 || r >= mat.length || c < 0 || c >= mat.length) {
            return;
        }

        if (mat[r][c] != 0) return;

        mat[r][c] = s.charAt(pos++);

        if (r+rv < 0 || r+rv >= mat.length || c+cv < 0 || c+cv >= mat.length || mat[r+rv][c+cv] != 0) {
//            System.out.println(mat[r][c]);
            go(r-1, c, -1, 0, mat, s, pos);
            go(r+1, c, 1, 0, mat, s, pos);
            go(r, c-1, 0, -1, mat, s, pos);
            go(r, c+1, 0, 1, mat, s, pos);
        } else {
            go(r+rv, c+cv, rv, cv, mat, s, pos);
        }
    }

    public void show (char[][] mat) {
        for (char[] a : mat) {
            for (char c : a) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
