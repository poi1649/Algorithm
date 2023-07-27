import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] dp;
    static int[][] cases;
    static int n;
    static int w;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());
        cases = new int[w + 1][2];
        dp = new int[w + 1][w + 1];

        for (int i = 0; i <= w; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 1; i <= w; i++) {
            final String[] s = br.readLine().split(" ");
            cases[i][0] = Integer.parseInt(s[0]);
            cases[i][1] = Integer.parseInt(s[1]);
        }
        System.out.println(calculate(0, 0));
        findPath(0, 0);
        System.out.print(sb);
    }

    static int calculate(int pOne, int pTwo) {
        if (pOne == w || pTwo == w) {
            return 0;
        }
        if (dp[pOne][pTwo] != -1) {
            return dp[pOne][pTwo];
        }

        int target = Math.max(pOne, pTwo) + 1;
        int distOne;
        int distTwo;

        if (pOne == 0) {
            distOne = dist(1, 1, cases[target][0], cases[target][1]);
        } else {
            distOne = dist(cases[pOne][0], cases[pOne][1], cases[target][0], cases[target][1]);
        }

        if (pTwo == 0) {
            distTwo = dist(n, n, cases[target][0], cases[target][1]);
        } else {
            distTwo = dist(cases[pTwo][0], cases[pTwo][1], cases[target][0], cases[target][1]);
        }
        distOne = distOne + calculate(target, pTwo);
        distTwo = distTwo + calculate(pOne, target);

        dp[pOne][pTwo] = Math.min(distOne, distTwo);
        return dp[pOne][pTwo];
    }

    static void findPath(int pOne, int pTwo) {
        if (pOne == w || pTwo == w) {
            return;
        }

        int target = Math.max(pOne, pTwo) + 1;
        int distOne;
        int distTwo;

        if (pOne == 0) {
            distOne = dist(1, 1, cases[target][0], cases[target][1]);
        } else {
            distOne = dist(cases[pOne][0], cases[pOne][1], cases[target][0], cases[target][1]);
        }

        if (pTwo == 0) {
            distTwo = dist(n, n, cases[target][0], cases[target][1]);
        } else {
            distTwo = dist(cases[pTwo][0], cases[pTwo][1], cases[target][0], cases[target][1]);
        }

        if (distOne + dp[target][pTwo] < distTwo + dp[pOne][target]) {
            sb.append(1).append(System.lineSeparator());
            findPath(target, pTwo);
        }
        else {
            sb.append(2).append(System.lineSeparator());
            findPath(pOne, target);
        }
    }

    static int dist(int x, int y, int xx, int yy) {
        return Math.abs(x - xx) + Math.abs(y - yy);
    }
}
