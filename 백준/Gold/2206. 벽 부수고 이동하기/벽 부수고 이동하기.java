import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static boolean[][] maps;
    static boolean[][] visited;
    static boolean[][] breakVisited;
    static int n;
    static int m;
    static Deque<int[]> routes = new LinkedList<>();
    static List<int[]> vectors =
            List.of(
                    new int[]{1, 0},
                    new int[]{-1, 0},
                    new int[]{0, 1},
                    new int[]{0, -1}
            );


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s1 = br.readLine().split(" ");
        n = Integer.parseInt(s1[0]);
        m = Integer.parseInt(s1[1]);
        maps = new boolean[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        breakVisited = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            final char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                maps[i][j + 1] = chars[j] == '0';
            }
        }
        visited[1][1] = true;
        routes.add(new int[]{1, 1, 0, 1});

        while (!routes.isEmpty()) {
            final int[] ints = routes.pollFirst();
            if (ints[0] == n && ints[1] == m) {
                System.out.println(ints[2] + 1);
                return;
            }
            check(ints[0], ints[1], ints[2], ints[3]);
        }
        System.out.println(-1);
    }

    static void check(int x, int y, int depth, int breakable) {
        for (int[] vector : vectors) {
            int dx = x + vector[0];
            int dy = y + vector[1];

            if (1 <= dx && dx <= n &&
                    1 <= dy && dy <= m &&
                    !visited[dx][dy]) {
                if (maps[dx][dy]) {
                    if (breakable == 0) {
                        if (breakVisited[dx][dy]) {
                            continue;
                        }
                        breakVisited[dx][dy] = true;
                    } else {
                        visited[dx][dy] = true;
                    }
                    routes.addLast(new int[]{dx, dy, depth + 1, breakable});
                    continue;
                }

                if (breakable == 1) {
                    breakVisited[dx][dy] = true;
                    routes.addLast(new int[]{dx, dy, depth + 1, 0});
                }
            }
        }
    }
}

