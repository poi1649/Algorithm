import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static int n, r;
    static BigInteger k;
    static Set<Integer> set = new HashSet<>();
    static ArrayList<Integer>[] graph;
    static int INF = 500_000_000;
    static String[] inputs;
    static long[][] dp;
    static int[][] mod;
    static StringBuilder sb = new StringBuilder();

    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {
        final Reader reader = new Reader();
        n = reader.nextInt();
        dp = new long[1 << n + 1][101];
        mod = new int[n + 1][101];
        for (int i = 0; i < 1 << n + 1; i++) {
            Arrays.fill(dp[i], -1);

        }
        inputs = new String[n + 1];

        for (int i = 1; i <= n; i++) {
            inputs[i] = reader.readLine();
            Arrays.fill(mod[i], -1);
        }
        BigInteger ans = new BigInteger("0");
        k = new BigInteger(reader.readLine());
        for (int i = 1; i <= n; i++) {
            ans = ans.add(new BigInteger(String.valueOf(findNumberOfCases(1 << i, new BigInteger(inputs[i]).mod(k).intValue()))));
        }
        BigInteger totalCase = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
            totalCase = totalCase.multiply(new BigInteger(String.valueOf(i)));
        }
        BigInteger gcd = ans.gcd(totalCase);
        System.out.println(ans.divide(gcd) + "/" + totalCase.divide(gcd));
    }

    static long findNumberOfCases(int bitSet, int remain) {
        if (dp[bitSet][remain] != -1) {
            return dp[bitSet][remain];
        }
        if (bitSet == (1 << n + 1) - 2) {
            if (remain == 0) {
                return dp[bitSet][remain] = 1;
            }
            return dp[bitSet][remain] = 0;
        }

        long ret = 0;
        for (int i = 1; i <= n; i++) {
            if ((bitSet & (1 << i)) == 0) {
                ret += findNumberOfCases(bitSet | (1 << i), getMod(i, remain));
            }
        }
        return dp[bitSet][remain] = ret;
    }

    static int getMod(int index, int remain) {
        if (mod[index][remain] != -1) {
            return mod[index][remain];
        }
        return mod[index][remain] = new BigInteger(remain + "" + inputs[index]).mod(k).intValue();
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

