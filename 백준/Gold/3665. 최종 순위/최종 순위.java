
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int n;
    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    static Deque<Integer> deque = new LinkedList<>();
    

    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        final int t = reader.nextInt();
        final StringBuilder realSb = new StringBuilder();
        for (int q = 0; q < t; q++) {

            n = reader.nextInt();
            graph = new ArrayList[n + 1];
            inDegree = new int[n + 1];
            sb = new StringBuilder();
            final ArrayList<Integer> left = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
                left.add(i);
            }
            Arrays.fill(inDegree, n);
            left.remove(0);
            for (int i = 1; i <= n; i++) {
                final int target = reader.nextInt();
                left.remove(Integer.valueOf(target));
                graph[target].addAll(left);
                inDegree[target] = n - left.size() - 1;
            }
            int m = reader.nextInt();
            for (int i = 0; i < m; i++) {
                int a = reader.nextInt();
                int b = reader.nextInt();
                if (graph[a].contains(b)) {
                    graph[a].remove(Integer.valueOf(b));
                    graph[b].add(a);
                    inDegree[a]++;
                    inDegree[b]--;
                    continue;
                }
                graph[a].add(b);
                graph[b].remove(Integer.valueOf(a));
                inDegree[a]--;
                inDegree[b]++;
            }
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    deque.add(i);
                }
            }
            while (!deque.isEmpty()) {
                if (deque.size() != 1) {
                    realSb.append("?").append("\n");
                    deque.clear();
                    continue;
                }
                int target = deque.poll();
                check(target);
            }
            final String result = sb.toString();
            if (result.split(" ").length != n) {
                realSb.append("IMPOSSIBLE").append("\n");
                continue;
            }
            realSb.append(sb).append("\n");
        }
        System.out.println(realSb);
    }

    static void check(int target) {
        final int size = graph[target].size();

        sb.append(target).append(" ");
        for (int i = 0; i < size; i++) {
            int next = graph[target].get(i);
            inDegree[next]--;
            if (inDegree[next] == 0) {
                deque.add(next);
            }
        }
    }
}


class Reader {

    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(
                new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                if (cnt != 0) {
                    break;
                } else {
                    continue;
                }
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }
}

