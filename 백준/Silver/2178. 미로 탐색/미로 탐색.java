import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static boolean[][] maps;
    static boolean[][] visited;
    static int n;
    static int m;
    static Deque<int[]> routes = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        maps = new boolean[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            final char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                maps[i][j + 1] = chars[j] == '1';
            }
        }
        routes.add(new int[]{1, 1, 1});
        visited[1][1] = true;

        while (!routes.isEmpty()) {
            final int[] ints = routes.pollFirst();
            if (ints[0] == n && ints[1] == m) {
                System.out.println(ints[2]);
                return;
            }
            check(ints[0], ints[1], ints[2]);
        }
    }

    static void check(int x, int y, int depth) {

        if (x < n && !visited[x + 1][y] && maps[x + 1][y]) {
            visited[x + 1][y] = true;
            final int[] ints = {x + 1, y, depth + 1};
            routes.addLast(ints);
        }
        if (x > 1 && !visited[x - 1][y] && maps[x - 1][y]) {
            visited[x - 1][y] = true;
            final int[] ints = {x - 1, y, depth + 1};
            routes.addLast(ints);
        }
        if (y > 1 && !visited[x][y - 1] && maps[x][y - 1]) {
            visited[x][y - 1] = true;
            final int[] ints = {x, y - 1, depth + 1};
            routes.addLast(ints);
        }
        if (y < m && !visited[x][y + 1] && maps[x][y + 1]) {
            visited[x][y + 1] = true;
            final int[] ints = {x, y + 1, depth + 1};
            routes.addLast(ints);
        }
    }
}

