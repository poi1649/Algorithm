import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] shortest;
    static int INF = 1_000_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int m = Integer.parseInt(br.readLine());
        shortest = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(shortest[i], INF);
        }

        for (int i = 0; i < m; i++) {
            final String[] s = br.readLine().split(" ");
            final int a = Integer.parseInt(s[0]);
            final int b = Integer.parseInt(s[1]);
            final int c = Integer.parseInt(s[2]);
            shortest[a][b] = Math.min(shortest[a][b], c);
        }

        for (int i = 1; i <= n; i++) {
            shortest[i][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    shortest[j][k] = Math.min(shortest[j][i] + shortest[i][k], shortest[j][k]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (shortest[i][j] == INF) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(shortest[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}
