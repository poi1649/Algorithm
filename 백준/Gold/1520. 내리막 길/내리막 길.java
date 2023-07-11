import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] ints;
    static long[][] wasAnswer;
    static boolean[][] visited;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        ints = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        wasAnswer = new long[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            final int[] s1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(s1, 0, ints[i], 1, s1.length);
        }
        wasAnswer[n][m] = 1;
        dfs(1, 1);

        System.out.println(wasAnswer[1][1]);
    }

    static boolean dfs(int i, int j) {
        if (visited[i][j] || (i == n && j == m)) {
            return wasAnswer[i][j] > 0;
        }

        boolean result = false;

        if (i != 1 && ints[i - 1][j] < ints[i][j]) {
            if (dfs(i - 1, j)) {
                result = true;
                wasAnswer[i][j] += wasAnswer[i - 1][j];
            }
        }
        if (i != n && ints[i + 1][j] < ints[i][j]) {
            if (dfs(i + 1, j)) {
                result = true;
                wasAnswer[i][j] += wasAnswer[i + 1][j];

            }
        }
        if (j != 1 && ints[i][j - 1] < ints[i][j]) {
            if (dfs(i, j - 1)) {
                result = true;
                wasAnswer[i][j] += wasAnswer[i][j - 1];
            }
        }
        if (j != m && ints[i][j + 1] < ints[i][j]) {
            if (dfs(i, j + 1)) {
                result = true;
                wasAnswer[i][j] += wasAnswer[i][j + 1];
            }

        }
        visited[i][j] = true;
        return result;
    }
}


