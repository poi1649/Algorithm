import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int[][] dp;
    static int n;
    static int k;
    static Deque<Integer> deque = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
        dp = new int[100001][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        dp[n][0] = 0;
        deque.add(n);
        while (!deque.isEmpty()) {
            check(deque.pollFirst());
        }

        System.out.println(dp[k][0]);

        sb.append(k).append(" ");
        if (k == n) {
            System.out.print(sb);
            return;
        }

        int pre = k;
        while (pre != n) {
            pre = dp[pre][1];
            sb.insert(0, pre + " ");
        }
        System.out.println(sb);
    }

    static void check(int index) {
        if (((index * 2) <= 100000) && (dp[index * 2][0] > dp[index][0] + 1)) {
            dp[index * 2][0] = dp[index][0] + 1;
            dp[index * 2][1] = index;
            deque.addLast(index * 2);
        }

        if ((index < 100000) && (dp[index + 1][0] > dp[index][0] + 1)) {
            dp[index + 1][0] = dp[index][0] + 1;
            dp[index + 1][1] = index;
            deque.addLast(index + 1);
        }

        if ((index > 0) && (dp[index - 1][0] > dp[index][0] + 1)) {
            dp[index - 1][0] = dp[index][0] + 1;
            dp[index - 1][1] = index;
            deque.addLast(index - 1);
        }
    }
}
