import java.util.*;
import java.io.*;

public class EmailSort {
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

    public class Email implements Comparable<Email> {
        String name;
        String provider;

        public Email (String email) {
            String[] s = email.split("@");
            this.name = s[0];
            this.provider = s[1];
        }

        @Override
        public int compareTo(Email o) {
            int pcomp = this.provider.compareTo(o.provider);

            if (pcomp == 0) {
                return this.name.compareTo(o.name);
            }

            return pcomp;
        }

        @Override
        public String toString() {
            return this.name+"@"+this.provider;

        }
    }

    public static void main(String[] args) {
        new EmailSort().run();
    }

    public void run () {
        IO f = new IO();

        int t = f.nextInt();

        PriorityQueue<Email> pq = new PriorityQueue<>();

        while (t-- > 0) {
            String s = f.next();
            pq.add(new Email(s));
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        f.close();
    }
}
