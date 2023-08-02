import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int n, m, x;
    static int result = 0;
    static boolean[] cleared;
    static int[] targets;
    static boolean[] visited;
    static final Deque<Integer> deque = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = parseInt(br.readLine());
        for (int k = 0; k < t; k++) {
            n = parseInt(br.readLine());
            visited = new boolean[n + 1];
            cleared = new boolean[n + 1];
            targets = new int[n + 1];
            result = 0;
            final String[] s = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                targets[i + 1] = parseInt(s[i]);
            }
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    continue;
                }
                check(i);
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    static void check(int target) {

        deque.addLast(target);
        visited[target] = true;
        int last = dfs(targets[target]);

        while (!deque.isEmpty()) {
            final int cur = deque.pollFirst();
            if (cur == last) {
                break;
            }
            result++;
        }
        deque.clear();
    }

    static int dfs(int cur) {
        if (visited[cur]) {
            return cur;
        }
        visited[cur] = true;
        deque.addLast(cur);
        return dfs(targets[cur]);
    }
}

