import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    static int[][][] w = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                Arrays.fill(w[i][j], Integer.MAX_VALUE);
            }
        }
        w[0][0][0] = 1;

        while (true) {
            final String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);
            if (a == -1 && b == -1 && c == -1) {
                return;
            }
            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c));
        }
    }

    public static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            a = 20;
            b = 20;
            c = 20;
        }

        if (a < b && b < c) {
            int i = w[a][b][c - 1];
            if (i == Integer.MAX_VALUE) {
                w[a][b][c - 1] = w(a, b, c - 1);
                i = w[a][b][c - 1];
            }

            int i1 = w[a][b - 1][c - 1];
            if (i1 == Integer.MAX_VALUE) {
                w[a][b - 1][c - 1] = w(a, b - 1, c - 1);
                i1 = w[a][b - 1][c - 1];
            }

            int i2 = w[a][b - 1][c];
            if (i2 == Integer.MAX_VALUE) {
                w[a][b - 1][c] = w(a, b - 1, c);
                i2 = w[a][b - 1][c];
            }

            return i + i1 - i2;
        }

        int i = w[a - 1][b][c];
        if (i == Integer.MAX_VALUE) {
            w[a - 1][b][c] = w(a - 1, b, c);
            i = w[a - 1][b][c];
        }
        int i1 = w[a - 1][b - 1][c];
        if (i1 == Integer.MAX_VALUE) {
            w[a - 1][b - 1][c] = w(a - 1, b - 1, c);
            i1 = w[a - 1][b - 1][c];
        }
        int i2 = w[a - 1][b][c - 1];
        if (i2 == Integer.MAX_VALUE) {
            w[a - 1][b][c - 1] = w(a - 1, b, c - 1);
            i2 = w[a - 1][b][c - 1];
        }
        int i3 = w[a - 1][b - 1][c - 1];
        if (i3 == Integer.MAX_VALUE) {
            w[a - 1][b - 1][c - 1] = w(a - 1, b - 1, c - 1);
            i3 = w[a - 1][b - 1][c - 1];
        }

        return i + i1 + i2 - i3;
    }

}
