import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static int q, n , m;

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
        var s= br.readLine().split(" ");
        int k = parseInt(s[0]);
        m = parseInt(s[1]);
        int[][] dp = new int[1001][1001];
        int[] fib = new int[300001];
        int[] iter = new int[100001];
        int iterLength = 0;
        int iterStart = 0;
        fib[1] = 1;
        fib[2] = 1;
        dp[1][1] = 1;
        for (int i = 3; i < 100001; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            fib[i] %= m;
            if (dp[fib[i - 1]][fib[i]] == 0) {
                dp[fib[i - 1]][fib[i]] = i - 1;
                continue;
            }
            iterLength = i - 2  - dp[fib[i - 1]][fib[i]] + 1;
            iterStart = dp[fib[i - 1]][fib[i]];
            System.arraycopy(fib, dp[fib[i - 1]][fib[i]], iter, 0, iterLength);
            break;
        }
        
        int[] fibNew = new int[300001];
        final AtomicInteger count = new AtomicInteger(1);
        for (int i = 1; i < iterStart; i++) {
            String.valueOf(i).chars().forEachOrdered(c -> fibNew[count.getAndIncrement()] = c - '0');
        }
        int nonIterLength = count.get() - 1;

        int[] iterNew = new int[300001];
        count.set(1);
        for (int i = 0; i < iterLength; i++) {
            String.valueOf(iter[i]).chars().forEachOrdered(c -> iterNew[count.getAndIncrement()] = c - '0');
        }
        int iterNewLength = count.get() - 1;

        for (int i = 0; i < k; i++) {
            var curr = Long.parseLong(br.readLine());
            if (curr <= nonIterLength) {
                System.out.println(fibNew[(int) curr]);
                continue;
            }
            curr -= nonIterLength;
            curr %= iterNewLength;
            System.out.println(iterNew[(int) curr]);
        }
    }
}
