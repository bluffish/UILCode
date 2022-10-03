import java.util.*;
import java.io.*;

public class Bryan {
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
        new Bryan().run();
    }

    public void run () {
        IO f = new IO();

        int t = f.nextInt();
        int tcase = 1;
        while (t-- > 0) {
            int n = f.nextInt();
            int s = 0;

            for (int i = 0; i < n; i++) {
                f.next();
                s += f.nextInt();
            }

            if (s == 0) {
                System.out.printf("Case #%d: Phew, broke even!\n", tcase++);
            } else if (s > 0) {
                System.out.printf("Case #%d: Wow, Bryan saved $%d\n", tcase++, s);
            } else {
                System.out.printf("Case #%d: Oh no! Bryan has to borrow  $%d\n", tcase++, -s);
            }
        }

        f.close();
    }
}
