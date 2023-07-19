import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int[] maps;
    static boolean[] visited;
    static int n;
    static int m;
    static Deque<int[]> routes = new LinkedList<>();


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        maps = new int[101];
        visited = new boolean[101];

        for (int i = 0; i < n + m; i++) {
            final String[] s1 = br.readLine().split(" ");
            maps[Integer.parseInt(s1[0])] = Integer.parseInt(s1[1]);
        }
        visited[1] = true;
        routes.add(new int[]{1, 0});

        while (!routes.isEmpty()) {
            final int[] target = routes.pollFirst();
            if (target[0] == 100) {
                System.out.println(target[1]);
                return;
            }
            check(target[0], target[1]);
        }
    }

    static void check(int target, int depth) {
        for (int i = 1; i <= 6; i++) {
            final int go = target + i;
            if (go <= 100 && !visited[go]) {
                visited[go] = true;
                if (maps[go] != 0) {
                    if (!visited[maps[go]]) {
                        visited[maps[go]] = true;
                        routes.addLast(new int[]{maps[go], depth + 1});
                    }
                } else {
                    routes.addLast(new int[]{go, depth + 1});
                }
            }
        }
    }
}

