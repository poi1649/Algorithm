import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int t;
    static int n;
    static int m;
    static int b;
    static Set<String> list = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        b = parseInt(s[2]);
        int[][] map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            final var s1 = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                final var k = parseInt(s1[j - 1]);
                map[i][j] = k;
            }
        }
        int index = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 256; i++) {
            int countOne = 0;
            int countTwo = 0;
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    if (map[j][k] == i) {
                        continue;
                    }
                    if (map[j][k] > i) {
                        countOne += (map[j][k] - i);
                        continue;
                    }
                    countTwo += (i - map[j][k]);
                }
            }
            if (b + countOne < countTwo) {
                continue;
            }
            final var a = (countOne * 2) + countTwo;
            if (ans >= a) {
                ans = a;
                index = i;
            }
        }
        System.out.println(ans + " " + index);
    }
}


