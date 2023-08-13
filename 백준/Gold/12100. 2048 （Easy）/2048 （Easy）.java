import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static long count;
    static int[][] map;
    static boolean[][] visited;
    static char[] directions = {'l', 'r', 'u', 'd'};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());;
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr.length; j++) {
                map[i][j + 1] = arr[j];
                max = Math.max(max, arr[j]);
            }
        }
        System.out.println(move(map, 0, max));
    }

    static int move(int[][] map, int depth, int max) {
        if (depth == 5) {
            return max;
        }
        int[][] temp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            int index = 1;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                boolean flag = false;
                for (int k = j + 1; k <= n; k++) {
                    if (map[i][k] == 0) {
                        continue;
                    }
                    if (map[i][j] == map[i][k]) {
                        temp[i][index++] = map[i][j] * 2;
                        max = Math.max(max, map[i][j] * 2);
                        j = k;
                        flag = true;

                    } else {
                        temp[i][index++] = map[i][j];
                        j = k - 1;
                        flag = true;

                    }
                    break;
                }
                if (!flag) {
                    temp[i][index++] = map[i][j];
                }
            }
        }
        if (!equals(map, temp)) {
            max = Math.max(move(temp, depth + 1, max), max);
        }

        temp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            int index = 1;
            for (int j = 1; j <= n; j++) {
                if (map[j][i] == 0) {
                    continue;
                }
                boolean flag = false;
                for (int k = j + 1; k <= n; k++) {
                    if (map[k][i] == 0) {
                        continue;
                    }
                    if (map[j][i] == map[k][i]) {
                        temp[index++][i] = map[j][i] * 2;
                        max = Math.max(max, map[j][i] * 2);
                        j = k;
                        flag = true;

                    } else {
                        temp[index++][i] = map[j][i];
                        j = k - 1;
                        flag = true;
                    }
                    break;
                }
                if (!flag) {
                    temp[index++][i] = map[j][i];
                }
            }
        }
        if (!equals(map, temp)) {
            max = Math.max(move(temp, depth + 1, max), max);
        }

        temp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            int index = n;
            for (int j = n; j >= 1; j--) {
                if (map[i][j] == 0) {
                    continue;
                }
                boolean flag = false;
                for (int k = j - 1; k >= 1; k--) {
                    if (map[i][k] == 0) {
                        continue;
                    }
                    if (map[i][j] == map[i][k]) {
                        temp[i][index--] = map[i][j] * 2;
                        max = Math.max(max, map[i][j] * 2);
                        j = k;
                        flag = true;
                    } else {
                        temp[i][index--] = map[i][j];
                        j = k + 1;
                        flag = true;
                    }
                    break;
                }
                if (!flag) {
                    temp[i][index--] = map[i][j];
                }
            }
        }
        if (!equals(map, temp)) {
            max = Math.max(move(temp, depth + 1, max), max);
        }

        temp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            int index = n;
            for (int j = n; j >= 1; j--) {
                if (map[j][i] == 0) {
                    continue;
                }
                boolean flag = false;
                for (int k = j - 1; k >= 1; k--) {
                    if (map[k][i] == 0) {
                        continue;
                    }
                    if (map[j][i] == map[k][i]) {
                        temp[index--][i] = map[j][i] * 2;
                        max = Math.max(max, map[j][i] * 2);
                        j = k;
                        flag = true;
                    } else {
                        temp[index--][i] = map[j][i];
                        j = k + 1;
                        flag = true;
                    }
                    break;
                }
                if (!flag) {
                    temp[index--][i] = map[j][i];
                }
            }
        }
        if (!equals(map, temp)) {
            max = Math.max(move(temp, depth + 1, max), max);
        }
        return max;
    }

    static boolean equals(int[][] map, int[][] temp) {
        for (int i = 1; i <= n; i++) {
            if (!Arrays.equals(map[i], temp[i])) {
                return false;
            }
        }
        return true;
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

