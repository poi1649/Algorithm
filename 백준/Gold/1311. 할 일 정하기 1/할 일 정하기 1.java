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
    static int[][] cost;
    static int[][] dp;

    static StringBuilder sb = new StringBuilder();

    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        cost = new int[n + 1][n + 1];
        dp = new int[n + 1][(1 << (n + 1))];

        for (int i = 1; i <= n; i++) {
            final String[] s = br.readLine().split(" ");
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < s.length; j++) {
                cost[i][j + 1] = parseInt(s[j]);
            }
        }

        final int checked = check(0, 1);
        System.out.println(checked);
    }

    static int check(int bitSet, int row) {
        if (row == n + 1) {
            return 0;
        }

        if (dp[row][bitSet] != -1) {
            return dp[row][bitSet];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if ((bitSet & (1 << i)) == 0) {
                min = Math.min(
                        min,
                        cost[row][i] + check(bitSet | (1 << i), row + 1)
                );
            }
        }
        return dp[row][bitSet] = min;
    }
}

