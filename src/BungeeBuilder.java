import java.util.*;
import java.io.*;

public class BungeeBuilder {
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
        new BungeeBuilder().run();
    }

    public void run () {
        IO f = new IO();

        int t = f.nextInt();
        int[] a = new int[t];
        for (int i = 0; i < t; i++) {
            a[i] = f.nextInt();
        }


        int max_index = 0;

        for (int i = 0; i < t; i++) {
            if (a[i] >= a[max_index])
                max_index = i;
        }

        int max_left = 0;
        int max_right = 0;
        int min_left = Integer.MAX_VALUE;
        int min_right = Integer.MAX_VALUE;

        for (int i = 0; i < max_index; i++) {
            max_left = Math.max(max_left, a[i]);
            min_left = Math.min(min_left, a[i]);
        }

        for (int i = max_index+1; i < t; i++) {
            max_right = Math.max(max_right, a[i]);
            min_right = Math.min(min_right, a[i]);
        }

        int left_distance = max_left-min_left;
        int right_distance = max_right-min_right;

        System.out.println(Math.max(left_distance, right_distance));

        f.close();
    }
}
