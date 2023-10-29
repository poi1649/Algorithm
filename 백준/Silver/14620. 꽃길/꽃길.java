import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static Deque<Integer> deque = new LinkedList<>();
    static int[][] cost = new int[11][11];
    static boolean[][] put = new boolean[11][11];
    static int t;
    static int ans = Integer.MAX_VALUE;
    static int n;

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

    public static void process(BufferedReader br, int t) throws IOException {
        n = parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            final var s = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                cost[i][j] = parseInt(s[j - 1]);
            }
        }
        calc(0, 2, 2, 0);
        System.out.println(ans);
    }

    public static void calc(int depth, int x, int y, int sum) {
        if (depth == 3) {
            ans = Math.min(sum, ans);
            return;
        }

        for (int j = y; j < n; j++) {
            if (put[x][j] || put[x + 1][j] || put[x][j + 1] || put[x - 1][j] || put[x][j - 1]) {
                continue;
            }
            put[x][j] = true;
            put[x + 1][j] = true;
            put[x][j + 1] = true;
            put[x - 1][j] = true;
            put[x][j - 1] = true;
            int temp = cost[x][j] + cost[x + 1][j] + cost[x][j + 1] + cost[x - 1][j] + cost[x][j - 1];
            calc(depth + 1, x, j + 2, sum + temp);
            put[x][j] = false;
            put[x + 1][j] = false;
            put[x][j + 1] = false;
            put[x - 1][j] = false;
            put[x][j - 1] = false;
        }

        for (int i = x + 1; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (put[i][j] || put[i + 1][j] || put[i][j + 1] || put[i - 1][j] || put[i][j - 1]) {
                    continue;
                }
                put[i][j] = true;
                put[i + 1][j] = true;
                put[i][j + 1] = true;
                put[i - 1][j] = true;
                put[i][j - 1] = true;
                int temp = cost[i][j] + cost[i + 1][j] + cost[i][j + 1] + cost[i - 1][j] + cost[i][j - 1];
                calc(depth + 1, i, j + 2, sum + temp);
                put[i][j] = false;
                put[i + 1][j] = false;
                put[i][j + 1] = false;
                put[i - 1][j] = false;
                put[i][j - 1] = false;
            }
        }
    }
}


