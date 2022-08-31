import java.util.*;
import java.io.*;

public class FightClub {
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
        new FightClub().run();
    }

    public void run () {
        IO f = new IO();

        int t = f.nextInt();

        while (t-- > 0) {
            int n = f.nextInt();
            String[] s = new String[n+2];
            s[0] = "*".repeat(n+2);
            s[s.length-1] = "*".repeat(n+2);
            int countup = 2;

            for (int i = 1; i <= n; i++) {

                String ins = "";

                int amount = 0;
                if (Math.ceil((n+2)/2.0)-i >= 1) {
                    amount = (int)Math.ceil((n + 2) / 2.0) - i;
                    ins += "*".repeat(amount);
                } else {
                    amount = countup;
                    ins += "*".repeat(countup);
                    countup++;
                }

                ins += " ".repeat((n+2 - (amount*2)));
                ins += "*".repeat(amount);
                s[i] = ins;
            }

            for (String ss : s) {
                System.out.println(ss);
            }
            System.out.println();
        }

        f.close();
    }
}
