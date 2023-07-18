import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static boolean[][] maps;
    static boolean[][] visited;
    static int n;
    static HashMap<Integer, Integer> hashMap;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        maps = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            final char[] line = br.readLine().toCharArray();
            for (int j = 0; j < line.length; j++) {
                final boolean target = line[j] == '1';
                maps[i][j + 1] = target;
            }
        }
        hashMap = new HashMap<>();
        final AtomicInteger integer = new AtomicInteger(0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!visited[i][j] && maps[i][j]) {
                    visited[i][j] = true;
                    integer.getAndIncrement();
                    hashMap.put(integer.get(), 0);
                    check(i, j, integer.get());
                }
            }
        }

        final StringBuilder sb = new StringBuilder();
        System.out.println(hashMap.size());
        hashMap.values().stream().sorted().forEach(i -> sb.append(i).append(System.lineSeparator()));
        System.out.print(sb);
    }

    static void check(int x, int y, int order) {
        hashMap.merge(order, 1 , Integer::sum);

        if (x < n && !visited[x + 1][y] && maps[x + 1][y]) {
            visited[x + 1][y] = true;
            check(x + 1, y, order);
        }
        if (x > 1 && !visited[x - 1][y] && maps[x - 1][y]) {
            visited[x - 1][y] = true;
            check(x - 1, y, order);
        }
        if (y < n && !visited[x][y + 1] && maps[x][y + 1]) {
            visited[x][y + 1] = true;
            check(x, y + 1, order);
        }
        if (y > 1 && !visited[x][y - 1] && maps[x][y - 1]) {
            visited[x][y - 1] = true;
            check(x, y - 1, order);
        }
    }
}

