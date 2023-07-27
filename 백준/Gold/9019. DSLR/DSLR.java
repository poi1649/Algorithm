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
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            dp = new int[10000][3];
            for (int j = 0; j < 10000; j++) {
                dp[j][0] = Integer.MAX_VALUE;
            }
            final String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            k = Integer.parseInt(s[1]);
            dp[n][0] = 0;
            deque.add(n);

            while (!deque.isEmpty()) {
                final int target = deque.pollFirst();
                check(target);
            }
            sb = new StringBuilder();
            findCommand(k);
            System.out.println(sb);
        }
    }

    static void findCommand(int target) {
        if(dp[target][2] == 0) {
            return;
        }
        sb.insert(0, convert(dp[target][2]));
        findCommand(dp[target][1]);
    }

    static String convert(int command) {
        if (command == 1) {
            return "D";
        }
        if (command == 2) {
            return "S";
        }
        if (command == 3) {
            return "L";
        }
        return "R";
    }

    static void check(int target) {
        final int d = D(target);
        if (dp[d][0] > dp[target][0] + 1) {

            dp[d][0] = dp[target][0] + 1;
            dp[d][1] = target;
            dp[d][2] = 1;

            deque.addLast(d);
        }

        final int s = S(target);
        if (dp[s][0] > dp[target][0] + 1) {

            dp[s][0] = dp[target][0] + 1;
            dp[s][1] = target;
            dp[s][2] = 2;

            deque.addLast(s);
        }

        final int l = L(target);
        if (dp[l][0] > dp[target][0] + 1) {

            dp[l][0] = dp[target][0] + 1;
            dp[l][1] = target;
            dp[l][2] = 3;

            deque.addLast(l);
        }

        final int r = R(target);
        if (dp[r][0] > dp[target][0] + 1) {

            dp[r][0] = dp[target][0] + 1;
            dp[r][1] = target;
            dp[r][2] = 4;

            deque.addLast(r);
        }

    }

    static int D(int target) {
        return (target * 2) % 10000;
    }

    static int S(int target) {
        return target == 0 ? 9999 : target - 1;
    }

    static int L(int target) {
        final int temp = target * 10;
        return (temp / 10000) + (temp % 10000);
    }

    static int R(int target) {
        return target / 10 + ((target % 10) * 1000);
    }

    // D ->  (n*2) % 10000;
    // S ->   n - 1 if 0 ? 10000
    // R ->   n/10 + n%10 * 1000
    // L ->   (n* 10)/10000 + (n*10)%10000
}
