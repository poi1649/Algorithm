import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, m;
    static int[] dx = {0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        n = parseInt(br.readLine());
        int t = parseInt(br.readLine());

        int[][] ts = new int[n + 1][21];
        int[] tMax = new int[n + 1];
        boolean[][] canGo = new boolean[n + 1][21];
        boolean[][] visited = new boolean[n + 1][21];

        ts[0][1] = t;
        tMax[0] = t;
        canGo[0][1] = true;
        visited[0][1] = true;

        for (int i = 1; i <= n; i++) {
            final var split = br.readLine().split(" ");
            m = parseInt(split[0]);
            for (int j = 1; j <= m; j++) {
                canGo[i][parseInt(split[j])] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= 20; j++) {
                if (!canGo[i][j]) {
                    continue;
                }
                for (int dx1 : dx) {
                    int prev = j + dx1;
                    if (prev < 1 || prev > 20) {
                        continue;
                    }
                    if (visited[i - 1][prev]) {
                        visited[i][j] = true;
                        ts[i][j] = Math.max(ts[i][j], ts[i - 1][prev]);
                    }
                }

                int prev = j / 2;
                if (j % 2 == 0 && visited[i - 1][prev]) {
                    visited[i][j] = true;
                    ts[i][j] = Math.max(ts[i][j], ts[i - 1][prev]);
                }

                if (j == 20) {
                    for (int pre = 11; pre < 20; pre++) {
                        if (visited[i - 1][pre]) {
                            visited[i][j] = true;
                            ts[i][j] = Math.max(ts[i][j], ts[i - 1][pre]);
                        }
                    }
                }

                if (!visited[i][j] && tMax[i - 1] > 0) {
                    visited[i][j] = true;
                    ts[i][j] = tMax[i - 1] - 1;
                }
                max = Math.max(max, ts[i][j]);
            }
            tMax[i] = max;
        }

        int max = -1;
        boolean isPossible = false;
        for (int i = 1; i <= 20; i++) {
            if (visited[n][i]) {
                max = Math.max(max, ts[n][i]);
                isPossible = true;
            }
        }

        if (!isPossible) {
            System.out.println(-1);
            return;
        }
        System.out.println(t - max);
    }
}
