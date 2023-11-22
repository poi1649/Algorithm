import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int t;
    static int m;
    static int n;
    static int[] arr = new int[1001];
    static int[] dp = new int[1001];

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

    public static void process(BufferedReader br, int p) throws IOException {
        n = parseInt(br.readLine());
        final var s = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = parseInt(s[i - 1]);
        }
        int max = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = arr[i];
            max = Math.max(dp[i], max);
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }

        System.out.println(max);
    }
}


