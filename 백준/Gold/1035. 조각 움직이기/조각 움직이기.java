import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static int q;
    static int[][] arr = new int[6][6];
    static HashMap<Integer, Cord> points = new HashMap<>();
    static HashMap<Integer, Cord> points2 = new HashMap<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[] visited;
    static boolean[][] visited2 = new boolean[6][6];
    static int min = 9834;
    static Deque<Cord> qs = new LinkedList<>();

    static class Cord {
        int id, x, y;

        public Cord(final int id, final int x, final int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        int count = 1;
        for (int i = 1; i <= 5; i++) {
            var chars = br.readLine().toCharArray();
            for (int j = 1; j <= 5; j++) {
                if (chars[j - 1] == '*') {
                    arr[i][j] = count++;
                    points2.put(arr[i][j], new Cord(arr[i][j], i, j));
                }
            }
        }
        visited = new boolean[count];
        iterate(count - 1, 1, 0);
        System.out.println(min);
    }

    public static void iterate(int depth, int id, int dist) {
        if (depth == qs.size()) {
            for (final Cord cord : qs) {
                points.put(cord.id, cord);
                arr[cord.x][cord.y] = cord.id;
            }
            if (check(points.get(1), qs.size())) {
                min = Math.min(min, dist);
            }
            for (final Cord cord : qs) {
                arr[cord.x][cord.y] = 0;
            }
            return;
        }

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (!visited2[i][j]) {
                    visited2[i][j] = true;
                    qs.addLast(new Cord(id, i, j));
                    final var cur = points2.get(id);
                    int d = Math.abs(cur.x - i) + Math.abs(cur.y - j);
                    iterate(depth, id + 1, dist + d);
                    visited2[i][j] = false;
                    qs.pollLast();
                }
            }
        }
    }

    public static boolean check(Cord start, int depth) {
        Arrays.fill(visited, false);
        int count = 0;
        Deque<Cord> q = new LinkedList<>();
        q.add(start);
        visited[start.id] = true;
        while (!q.isEmpty()) {
            Cord cur = q.pollFirst();
            count++;
            if (count == depth) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if (x < 1 || x > 5 || y < 1 || y > 5) {
                    continue;
                }
                if (arr[x][y] == 0 || visited[arr[x][y]]) {
                    continue;
                }
                visited[arr[x][y]] = true;
                q.addLast(points.get(arr[x][y]));
            }
        }
        return false;
    }
}
