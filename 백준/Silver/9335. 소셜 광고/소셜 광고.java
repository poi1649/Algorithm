import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static StringBuilder ans = new StringBuilder();
    static int n;
    static boolean[] visited = new boolean[21];
    static List<Integer>[] g = new List[21];
    static int[] covered = new int[21];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var t = parseInt(br.readLine());
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        while (t-- != 0) {
            process(br);
        }
        System.out.print(ans);
    }

    public static void process(BufferedReader br) throws IOException {
        n = parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            final var s = br.readLine().split(" ");
            for (int j = 1; j < s.length; j++) {
                g[i].add(parseInt(s[j]));
            }
        }

        calc(0, 1);

        result = Math.min(result, n);
        ans.append(result).append("\n");
        result = Integer.MAX_VALUE;

        clearGraph();
    }

    public static void calc(int depth, int start) {
        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            if (covered[i] == 0) {
                flag = false;
                break;
            }
        }

        if (flag) {
            result = Math.min(result, depth);
            return;
        }

        if (start > n || depth > n) {
            return;
        }

        for (int i = start; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                covered[i]++;
                g[i].forEach(integer -> covered[integer]++);
                calc(depth + 1, i + 1);
                g[i].forEach(integer -> covered[integer]--);
                covered[i]--;
                visited[i] = false;
            }
        }
    }

    private static void clearGraph() {
        for (final List<Integer> integers : g) {
            integers.clear();
        }
    }
}


