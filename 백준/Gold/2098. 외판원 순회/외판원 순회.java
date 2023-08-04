import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static int n, r, q;
    static Set<Integer> set = new HashSet<>();
    static ArrayList<Integer>[] graph;
    static int INF = 500_000_000;
    static int[][] cost;
    static int[][][] dp;

    static StringBuilder sb = new StringBuilder();

    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        dp = new int[n + 1][n + 1][1 << (n + 1)];
        cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            final String[] s = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                cost[i][j] = parseInt(s[j - 1]);
                Arrays.fill(dp[i][j], -1);
            }
        }

        int result = INF;
        for (int start = 1; start <= n; start++) {
            int bitSet = 1 << start;
            final int checked = check(start, bitSet, start);
            result = Math.min(result, checked);
        }
        System.out.println(result);
    }

    static int check(int start, int bitSet, int current) {

        if (dp[start][current][bitSet] != -1) {
            return dp[start][current][bitSet];
        }

        if (bitSet == (1 << n + 1) - 2) {
            if (cost[current][start] != 0) {
                return dp[start][current][bitSet] = cost[current][start];
            } else {
                return dp[start][current][bitSet] = INF;
            }
        }

        int min = INF;
        for (int next = 1; next <= n; next++) {
            if ((cost[current][next] != 0) &&
                    ((bitSet & (1 << next)) == 0)) {
                min = Math.min(
                        min,
                        check(start, bitSet | (1 << next), next) + cost[current][next]
                );
            }
        }
        return dp[start][current][bitSet] = min;
    }
}

