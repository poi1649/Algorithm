import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int[][] maps;
    static int n;
    static int m;
    static Deque<int[]> routes = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            routes.clear();
            n = Integer.parseInt(br.readLine());
            final String[] start = br.readLine().split(" ");
            final String[] end = br.readLine().split(" ");
            int[] starts = new int[3];
            starts[0] = Integer.parseInt(start[0]);
            starts[1] = Integer.parseInt(start[1]);
            final int i1 = Integer.parseInt(end[0]);
            final int i2 = Integer.parseInt(end[1]);
            maps = new int[n + 1][n + 1];

            routes.add(starts);
            while (!routes.isEmpty()) {
                int[] target = routes.pollFirst();
                if (target[0] == i1 &&
                        target[1] == i2) {
                    System.out.println(target[2]);
                    break;
                }
                check(target[0], target[1], target[2]);
            }
        }
    }

    static void check(int x, int y, int depth) {
        if (2 + x <= n - 1 && y > 0 && maps[2 + x][y - 1] == 0) {
            maps[2 + x][y - 1] = depth + 1;
            routes.addLast(new int[]{2 + x, y - 1, depth + 1});
        }
        if (2 + x <= n - 1 && y < n - 1 && maps[2 + x][y + 1] == 0) {
            maps[2 + x][y + 1] = depth + 1;
            routes.addLast(new int[]{2 + x, y + 1, depth + 1});
        }

        if (2 + y <= n - 1 && x < n - 1 && maps[x + 1][2 + y] == 0) {
            maps[x + 1][2 + y] = depth + 1;
            routes.addLast(new int[]{x + 1, 2 + y, depth + 1});
        }
        if (2 + y <= n - 1 && x > 0 && maps[x - 1][2 + y] == 0) {
            maps[x - 1][2 + y] = depth + 1;
            routes.addLast(new int[]{x - 1, 2 + y, depth + 1});
        }

        if (x - 2 >= 0 && y > 0 && maps[x - 2][y - 1] == 0) {
            maps[x - 2][y - 1] = depth + 1;
            routes.addLast(new int[]{x - 2, y - 1, depth + 1});
        }
        if (x - 2 >= 0 && y < n - 1 && maps[x - 2][y + 1] == 0) {
            maps[x - 2][y + 1] = depth + 1;
            routes.addLast(new int[]{x - 2, y + 1, depth + 1});
        }

        if (y - 2 >= 0 && x < n - 1 && maps[x + 1][y - 2] == 0) {
            maps[x + 1][y - 2] = depth + 1;
            routes.addLast(new int[]{x + 1, y - 2, depth + 1});
        }
        if (y - 2 >= 0 && x > 0 && maps[x - 1][y - 2] == 0) {
            maps[x - 1][y - 2] = depth + 1;
            routes.addLast(new int[]{x - 1, y - 2, depth + 1});
        }
    }
}

