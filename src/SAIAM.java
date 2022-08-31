import java.util.*;
import java.io.*;

public class SAIAM {
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
        new SAIAM().run();
    }

    public void run () {
        IO f = new IO();

        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ*********";

        while (true) {
            int size = f.nextInt();
            if (size == 0) break;

            char[][] mat = new char[(int) Math.ceil(Math.sqrt(size))][(int) Math.ceil(Math.sqrt(size))];

            int dir = 1;
            int pos = 0;

            int top = 0, bottom = mat.length - 1, left = 0, right = mat.length - 1;

            while (top <= bottom && left <= right) {

                if (dir == 1) {
                    for (int i = left; i <= right; ++i) {
                        mat[top][i] = s.charAt(pos++);
                    }

                    ++top;
                    dir = 2;
                } else if (dir == 2) {
                    for (int i = top; i <= bottom; ++i) {
                        mat[i][right] = s.charAt(pos++);
                    }

                    --right;
                    dir = 3;
                } else if (dir == 3) {
                    for (int i = right; i >= left; --i) {
                        mat[bottom][i] = s.charAt(pos++);
                    }

                    --bottom;
                    dir = 4;
                } else if (dir == 4) {
                    for (int i = bottom; i >= top; --i) {
                        mat[i][left] = s.charAt(pos++);
                    }

                    ++left;
                    dir = 1;
                }
            }
            show(mat);
        }

        f.close();
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
