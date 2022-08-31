import java.util.*;
import java.io.*;

public class AmalgamatedArtichokes {
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
        new AmalgamatedArtichokes().run();
    }

    public void run () {
        IO f = new IO();

        int p = f.nextInt();
        int a = f.nextInt();
        int b = f.nextInt();
        int c = f.nextInt();
        int d = f.nextInt();
        int n = f.nextInt();

        double max_price =  p * (Math.sin(a + b) + Math.cos(c + d) + 2);
        double max_drop = 0;

        for (int i = 2; i <= n; i++) {
            double price = p * (Math.sin(a*i + b) + Math.cos(c*i + d) + 2);
//            System.out.println(price);
            max_price = Math.max(max_price, price);
            max_drop = Math.max(max_drop, max_price-price);
        }

        System.out.println(max_drop);

        f.close();
    }
}
