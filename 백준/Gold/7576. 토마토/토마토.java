import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int[][] maps;
    static boolean[][] visited;
    static int n;
    static int m;
    static int max = 0;
    static final List<int[]> vectors = List.of(
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, +1},
            new int[]{0, -1}
    );
    static Deque<int[]> routes = new LinkedList<>();


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[1]);
        m = Integer.parseInt(s[0]);
        maps = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            final int[] oneLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(oneLine, 0, maps[i], 1, m);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (maps[i][j] == 1) {
                    visited[i][j] = true;
                    routes.add(new int[]{i, j, 0});
                }
            }
        }

        while (!routes.isEmpty()) {
            final int[] target = routes.pollFirst();
            check(target[0], target[1], target[2]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (maps[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(max);
    }

    static void check(int x, int y, int depth) {
        max = Math.max(max, depth);
        for (int[] vector : vectors) {
            final int dx = x + vector[0];
            final int dy = y + vector[1];
            if (

                    1 <= dx && dx <= n &&
                            1 <= dy && dy <= m &&
                            maps[dx][dy] == 0 &&
                            !visited[dx][dy]

            ) {
                maps[dx][dy] = 1;
                visited[dx][dy] = true;
                routes.addLast(new int[]{dx, dy, depth + 1});
            }
        }
    }
}

