import java.util.*;
import java.io.*;

public class Ethan {
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
        new Ethan().run();
    }

    public void run () {
        IO f = new IO();

        int T = f.nextInt();

        while (T-- > 0) {
            int n = f.nextInt();

            int[][] m = new int[n*n][2];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int num = f.nextInt();

                    m[num-1] = new int[]{i, j};
                }
            }

            int[] prev = m[0];
            double dist = 0;

            for (int i = 1; i < m.length; i++) {
                dist += Math.sqrt(Math.pow(prev[0]-m[i][0], 2) + Math.pow(prev[1]-m[i][1], 2));
                prev = m[i];
            }

            System.out.println(dist);
        }

        f.close();
    }
}
