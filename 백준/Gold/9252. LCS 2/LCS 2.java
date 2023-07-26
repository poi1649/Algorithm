import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        int n = temp.length;
        char[] s1 = new char[temp.length + 1];
        System.arraycopy(temp, 0, s1, 1, temp.length);

        temp = br.readLine().toCharArray();
        int m = temp.length;
        char[] s2 = new char[temp.length + 1];
        System.arraycopy(temp, 0, s2, 1, temp.length);


        final int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1[i] == s2[j] && (dp[i][j] < dp[i - 1][j - 1] + 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        int max = dp[n][m];
        int index = max;
        int jMax = m;
        final StringBuilder sb = new StringBuilder();

        for (int i = n; i > 0; i--) {
            for (int j = m; j > 0; j--) {
                if (jMax >= j && (dp[i][j] == index) && (s1[i] == s2[j])) {
                    sb.insert(0, s1[i]);
                    index--;
                    jMax = j -1;
                    break;
                }
            }
        }

        System.out.println(max);
        System.out.println(sb);
    }
}
