import java.util.*;
import java.io.*;

public class AbandonedMineshaft {
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
        new AbandonedMineshaft().run();
    }

    public class Path implements Comparable<Path> {
        public int time;
        public int r;
        public int c;
        public int f;
        public int durability;

        public Path (int r, int c, int f, int time, int durability) {
            this.time = time;
            this.r = r;
            this.c = c;
            this.f = f;
            this.durability = durability;
        }

        @Override
        public int compareTo(Path o) {
            return Integer.compare(this.time, o.time);
        }
    }

    int OPEN = 0;
    int BREAKABLE = 1;
    int UNBREAKABLE = 2;
    int END = 3;

    public void run () {
        IO io = new IO();

        int t = io.nextInt();

        out: while (t-- > 0) {
            int floors = io.nextInt();
            int rows = io.nextInt();
            int cols = io.nextInt();
            int pick = io.nextInt();


            int[][][] m = new int[floors][rows][cols];

            int startr = 0;
            int startc = 0;
            int startf = 0;

            for (int f = 0; f < floors; f++) {
                for (int r = 0; r < rows; r++) {
                    String s = io.next();
                    for (int c = 0; c < cols; c++) {
                        if (s.charAt(c) == '.')
                            m[f][r][c] = OPEN;
                        if (s.charAt(c) == '#')
                            m[f][r][c] = UNBREAKABLE;
                        if (s.charAt(c) == '%')
                            m[f][r][c] = BREAKABLE;
                        if (s.charAt(c) == 'E')
                            m[f][r][c] = END;
                        if (s.charAt(c) == 'S') {
                            m[f][r][c] = OPEN;
                            startr = r;
                            startc = c;
                            startf = f;
                        }

                    }
                }
            }

            PriorityQueue<Path> pq = new PriorityQueue<>();
            pq.add(new Path(startr, startc, startf, 0, pick));

            int[] vr = {1, -1, 0, 0, 0, 0};
            int[] vc = {0, 0, 1, -1, 0, 0};
            int[] vf = {0, 0, 0, 0, 1, -1};

            while (!pq.isEmpty()) {
                Path current = pq.poll();

                int f = current.f;
                int r = current.r;
                int c = current.c;

                System.out.println(f + " " + r + " " + c);

                int time = current.time;

                if (m[f][r][c] == END) {
                    System.out.println((time) + " SECONDS");
                    continue out;
                }

//                System.out.println(time);
                if (current.durability < 0) continue;

                m[f][r][c] = UNBREAKABLE;

                for (int i = 0; i < 6; i++) {
                    int nf = f + vf[i];
                    int nr = r + vr[i];
                    int nc = c + vc[i];
                    int ntime = time;
                    int ndura = current.durability;

                    if (nf < 0 || nf >= floors || nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;

                    if (m[nf][nr][nc] == BREAKABLE) {
                        ntime += 3;
                        ndura--;
                    } else if (m[nf][nr][nc] == UNBREAKABLE) {
                        continue;
                    }

                    if (nf > f) {
                        ntime += 2;
                    } else if (nf < f) {
                        ntime += 3;
                    } else {
                        ntime++;
                    }

                    pq.add(new Path(nr, nc, nf, ntime, ndura));
                }
            }

            System.out.println("ur dead :D");
        }


        io.close();
    }
}
