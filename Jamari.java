import java.util.*;
import java.io.*;

public class Jamari {
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
        new Jamari().run();
    }

    public void run () {
        IO f = new IO();

        Queue<String> line = new LinkedList<>();
        String prev = "";

        while (f.hasNext()) {
            String raw = f.next();
            if (Character.isDigit(raw.charAt(0))) {
                int n = Integer.parseInt(raw);

                while (n-- > 0) {
                    prev = line.poll();
                }
            } else {
                if (raw.equals(">>>")) {
                    System.out.println(prev);
                    System.out.println(line.size());
                    line = new LinkedList<>();
                    prev = "";
                } else {
                    line.add(raw);
                }
            }
        }

        f.close();
    }
}
