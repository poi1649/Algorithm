import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int q, n, m;
    static List<Integer>[] numbers = new List[10];
    static int[][] dp = new int[10][1001];
    static int[] dx = {1, -1, 3, -3};

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
        for (int i = 0; i < 10; i++) {
            numbers[i] = new ArrayList<>();
        }
        numbers[0].addAll(List.of(7));
        numbers[1].addAll(List.of(2, 4));
        numbers[2].addAll(List.of(1, 3 ,5));
        numbers[3].addAll(List.of(2, 6));
        numbers[4].addAll(List.of(1, 5, 7));
        numbers[5].addAll(List.of(2, 4, 6, 8));
        numbers[6].addAll(List.of(3, 5, 9));
        numbers[7].addAll(List.of(4, 8, 0));
        numbers[8].addAll(List.of(5, 7, 9));
        numbers[9].addAll(List.of(6, 8));

        for (int i = 0; i < 10; i++) {
            dp[i][1] = 1;
            dp[i][2] = dp[i][1] * numbers[i].size();
        }

        for (int i = 3; i <= 1000; i++) {
            for (int j = 0; j < 10; j++) {
                for (final Integer k : numbers[j]) {
                    dp[j][i] += (dp[k][i - 1] % 1234567);
                }
                dp[j][i] %= 1234567;
            }
        }
        n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            m = parseInt(br.readLine());
            int ans = 0;
            for (int j = 0; j < 10; j++) {
                ans += (dp[j][m] % 1234567);
            }
            System.out.println(ans % 1234567);
        }
    }
}

