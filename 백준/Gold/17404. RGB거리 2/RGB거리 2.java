import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int n, r;
    static int[][] cost;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final Reader reader = new Reader();
        n = reader.nextInt();
        cost = new int[n + 1][3];
        dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            cost[i][0] = reader.nextInt();
            cost[i][1] = reader.nextInt();
            cost[i][2] = reader.nextInt();
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        };

        dp[1][0] = cost[1][0];
        dp[1][1] = 1001;
        dp[1][2] = 1001;
        check(2);
        int tempMinOne = Math.min(dp[n][1], dp[n][2]);

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = 1001;
        dp[1][1] = cost[1][1];
        dp[1][2] = 1001;

        check(2);
        int tempMinTwo = Math.min(dp[n][0], dp[n][2]);

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = 1001;
        dp[1][1] = 1001;
        dp[1][2] = cost[1][2];
        check(2);
        int tempMinThree = Math.min(dp[n][0], dp[n][1]);

        System.out.println(Math.min(tempMinOne, Math.min(tempMinTwo, tempMinThree)));
    }
    static void check(int depth) {
        if (depth > n) {
            return;
        }

        dp[depth][0] = Math.min(dp[depth -1][1], dp[depth - 1][2]) + cost[depth][0];
        dp[depth][1] = Math.min(dp[depth -1][0], dp[depth - 1][2]) + cost[depth][1];
        dp[depth][2] = Math.min(dp[depth -1][0], dp[depth - 1][1]) + cost[depth][2];

        check(depth + 1);
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

