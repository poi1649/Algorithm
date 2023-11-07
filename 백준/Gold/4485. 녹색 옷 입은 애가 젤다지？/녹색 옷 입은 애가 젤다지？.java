import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int t;
    static int n;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

    static class Node {

        public int x, y, dist;

        public Node(final int x, final int y, final int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (true) {
            if (process(br, cur++) == -1) {
                break;
            }
        }
        System.out.print(sb);
        br.close();
    }

    public static int process(BufferedReader br, int t) throws IOException {
        n = parseInt(br.readLine());
        if (n == 0) {
            return - 1;
        }
        int[][] map = new int[n + 1][n + 1];
        int nodes = n * n;
        int[] dist = new int[nodes + 1];
        boolean[] visited = new boolean[nodes + 1];

        for (int i = 1; i <= n; i++) {
            final var s = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = parseInt(s[j - 1]);
            }
        }

        for (int i = 2; i <= nodes; i++) {
                dist[i] = 987654321;

        }
        dist[1] = map[1][1];
        q.add(new Node(1, 1, dist[1]));

        while (!q.isEmpty()) {
            final var node = q.poll();
            int cur = id(node.x, node.y);
            int i = node.x;
            int j = node.y;
            int next;
            if (i - 1 > 0) {
                next = id(i - 1, j);
                if (!visited[next] && dist[next] >= node.dist + map[i - 1][j]) {
                    dist[next] = node.dist + map[i - 1][j];
                    q.add(new Node(i - 1, j, dist[next]));
                }
            }
            if (i + 1 <= n) {
                next = id(i + 1, j);
                if (!visited[next] && dist[next] >= node.dist + map[i + 1][j]) {
                    dist[next] = node.dist + map[i + 1][j];
                    q.add(new Node(i + 1, j, dist[next]));
                }
            }
            if (j - 1 > 0) {
                next = id(i, j - 1);
                if (!visited[next] && dist[next] >= node.dist + map[i][j - 1]) {
                    dist[next] = node.dist + map[i][j - 1];
                    q.add(new Node(i, j - 1, dist[next]));
                }
            }
            if (j + 1 <= n) {
                next = id(i, j + 1);
                if (!visited[next] && dist[next] >= node.dist + map[i][j + 1]) {
                    dist[next] = node.dist + map[i][j + 1];
                    q.add(new Node(i, j + 1, dist[next]));
                }
            }
            visited[cur] = true;
        }
        sb.append("Problem ").append(t).append(": ").append(dist[id(n, n)]).append('\n');
        return 1;
    }

    static int id(int i, int j) {
        return (i - 1) * n + j;
    }
}


