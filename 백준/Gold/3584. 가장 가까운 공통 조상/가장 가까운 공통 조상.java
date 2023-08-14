import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    static int n;
    static HashMap<Integer, Integer> parents = new HashMap<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        int t = parseInt(reader.readLine());
        for (int k = 0; k < t; k++) {
            n = parseInt(reader.readLine());
            visited = new boolean[n + 1];
            parents.clear();
            for (int i = 0; i < n - 1; i++) {
                String[] input = reader.readLine().split(" ");
                int a = parseInt(input[0]);
                int b = parseInt(input[1]);
                parents.put(b, a);
            }
            String[] input = reader.readLine().split(" ");
            int a = parseInt(input[0]);
            int b = parseInt(input[1]);
            findParent(a);
            int bParent = findParent(b);
            System.out.println(bParent);
        }
    }

    private static int findParent(int a) {
        if (visited[a] || !parents.containsKey(a)) {
            return a;
        }
        visited[a] = true;
        return findParent(parents.get(a));
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

