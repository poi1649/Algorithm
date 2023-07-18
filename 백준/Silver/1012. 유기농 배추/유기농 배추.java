import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] maps;
    static boolean[][] visited;
    static int n;
    static int m;
    static int k;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = Integer.parseInt(br.readLine());
        for (int z = 0; z < t; z++) {
            final String[] s = br.readLine().split(" ");
            m = Integer.parseInt(s[0]);
            n = Integer.parseInt(s[1]);
            k = Integer.parseInt(s[2]);
            maps = new boolean[n + 1][m + 1];
            visited = new boolean[n + 1][m + 1];

            for (int i = 0; i < k; i++) {
                final String[] s1 = br.readLine().split(" ");
                maps[Integer.parseInt(s1[1]) + 1][Integer.parseInt(s1[0]) + 1] = true;
            }

            int result = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (!visited[i][j] && maps[i][j]) {
                        visited[i][j] = true;
                        result++;
                        check(i, j);
                    }
                }
            }
            System.out.println(result);
        }
    }

    static void check(int x, int y) {

        if (x < n && !visited[x + 1][y] && maps[x + 1][y]) {
            visited[x + 1][y] = true;
            check(x + 1, y);
        }
        if (x > 1 && !visited[x - 1][y] && maps[x - 1][y]) {
            visited[x - 1][y] = true;
            check(x - 1, y);
        }
        if (y < m && !visited[x][y + 1] && maps[x][y + 1]) {
            visited[x][y + 1] = true;
            check(x, y + 1);
        }
        if (y > 1 && !visited[x][y - 1] && maps[x][y - 1]) {
            visited[x][y - 1] = true;
            check(x, y - 1);
        }
    }
}

