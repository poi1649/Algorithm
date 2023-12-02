import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, m, x;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }


    public static void process(BufferedReader br, int cur) throws IOException {
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        m = parseInt(split[1]);
        arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            var s = br.readLine().toCharArray();
            for (int j = 1; j <= s.length; j++) {
                arr[i][j] = s[j - 1] - '0';
            }
        }
        int max = -1;
        for (int i = -9; i <= 9; i++) {
            for (int j = -9; j <= 9; j++) {
                for (int k = 1; k <= n; k++) {
                    for (int l = 1; l <= m; l++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        max = Math.max(calc(i, j, k, l), max);
                    }
                }
            }
        }
        System.out.println(max);
    }

    public static int calc(int iGap, int jGap, int iStart, int jStart) {
        int i = iStart;
        int j = jStart;
        int num = 0;
        int max = -1;
        while (0 < i && i <= n && 0 < j && j <= m) {
            num *= 10;
            num += arr[i][j];
            if (isSquare(num)) {
                max = Math.max(num, max);
            }
            i += iGap;
            j += jGap;
        }
        return max;
    }

    public static boolean isSquare(int n)
    {
        long i = n;
        while (i * i > n) {
            i = (i + n / i) / 2;
        }
        return i * i == n;
    }
}
