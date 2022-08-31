import java.util.*;
import java.io.*;

public class GetGrinched {
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
        new GetGrinched().run();
    }

    public class Node implements Comparable<Node> {
        int r;
        int c;
        int weight;

        public Node (int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.weight = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    int OPEN = 0;
    int WALL = 1;
    int CHILD = 2;
    public void run () {
        IO f = new IO();

        int t = f.nextInt();

        while (t-- > 0) {
            int rows = f.nextInt();
            int cols = f.nextInt();
            Node[] children = new Node[f.nextInt()];
            int index = 0;
            int[][] m = new int[rows][cols];

            int origr = -1;
            int origc = -1;

            for (int r = 0; r < rows; r++) {
                String s = f.next();
                for (int c = 0; c < cols; c++) {
                    if (s.charAt(c) == '.')
                        m[r][c] = OPEN;
                    else if (s.charAt(c) == '#')
                        m[r][c] = WALL;
                    else if (s.charAt(c) == 'T') {
                        children[index++] = new Node(r, c, 0);
                        m[r][c] = CHILD;
                    }
                    else if (s.charAt(c) == 'R') {
                        m[r][c] = OPEN;
                        origr = r;
                        origc = c;
                    }
                }
            }

            int[] vr = {1, -1, 0, 0};
            int[] vc = {0, 0, 1, -1};

            int total = 0;
            int max = 0;
            int reached = 0;

            for (Node child : children) {
                PriorityQueue<Node> pq = new PriorityQueue<>();
                int[][] shadow = new int[rows][cols];
                pq.add(new Node(origr, origc, 0));

                while (!pq.isEmpty()) {
                    Node n = pq.poll();

                    shadow[n.r][n.c] = 1;

                    if (n.r == child.r && n.c == child.c) {
                        max = Math.max(max, n.weight);
                        total += n.weight*2;
                        reached++;
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nr = n.r + vr[i];
                        int nc = n.c + vc[i];

                        if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || m[nr][nc] == WALL || shadow[nr][nc] == 1) continue;
                        pq.add(new Node(nr, nc, n.weight+1));
                    }
                }
            }

            if (reached == children.length) {
                total -= max;
                System.out.println(total);
            } else {
                System.out.println("Get Grinched!");
            }
        }

        f.close();
    }
}
