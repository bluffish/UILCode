import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Space {
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
            r = new BufferedReader(new FileReader(name+".dat"));
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

    public static void main(String[] args) throws IOException {
        new Space().run();
    }

    int OPEN = 0;
    int WALL = 1;
    int END = 2;

    class Position {
        int[] loc;

        public Position (int[] loc) {
            this.loc = loc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Arrays.equals(loc, position.loc);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(loc);
        }

        public Position copy () {
            return new Position(loc.clone());
        }
    }

    class Node implements Comparable<Node> {
        Position pos;
        int time;

        public Node (Position pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(time, o.time);
        }

        public Node copy () {
            return new Node(pos.copy(), time);
        }
    }

    public void run () throws IOException {
        IO f = new IO("space");

        int t = f.nextInt();

        while (t-- > 0) {
            int dims = f.nextInt();
            int size = f.nextInt();

            HashMap<Position, Integer> m = new HashMap<>();

            Position pos = new Position(new int[dims]);
            Position current = new Position(new int[dims]);

            for (int i = 0; i < Math.pow(size, dims-1); i++) {
                String s = f.next();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '.') {
                        m.put(current.copy(), OPEN);
                    } else if (s.charAt(j) == '#')
                        m.put(current.copy(), WALL);
                    else if (s.charAt(j) == 'E')
                        m.put(current.copy(), END);
                    else {
                        m.put(current.copy(), OPEN);
                        pos = current.copy();
                    }

                    try {
                        inc(current.loc, size);
                    } catch (Exception e) {

                    }
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            HashSet<Position> visited = new HashSet<>();
            pq.add(new Node(pos, 0));;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (visited.contains(cur.pos)) continue;
                visited.add(cur.pos);

                if (m.get(cur.pos) == END) {
                    System.out.println(cur.time);
                    break;
                }

                for (int i = 0; i < cur.pos.loc.length; i++) {
                    Node n = cur.copy();
                    n.pos.loc[i]++;
                    n.time += i+1;

                    add(n, pq, size, m);
                    n.pos.loc[i] -=2 ;
                    add(n, pq, size, m);

                }
            }

        }

        f.close();
    }

    public void add (Node n, PriorityQueue<Node> pq, int size, HashMap<Position, Integer> m) {
        for (int i : n.pos.loc) {
            if (i < 0 || i >= size) {
                return;
            }
        }

        if (m.get(n.pos) == WALL) {
            return;
        }


        pq.add(n.copy());
    }
    public void inc (int[] m, int size) {
        m[0]++;
        for (int i = 0; i < m.length; i++) {
            if (m[i] >= size) {
                m[i] = 0;
                m[i+1]++;
            }
        }
    }
}
