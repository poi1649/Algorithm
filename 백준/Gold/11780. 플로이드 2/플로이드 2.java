import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    private static final int INF = 100_000_000;
    static int[][] shortest;
    static int[][] middle;
    static int n;
    static int m;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> deque = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        shortest = new int[n + 1][n + 1];
        middle = new int[n + 1][n + 1];

        for (int[] ints : shortest) {
            Arrays.fill(ints, INF);
        }

        for (int i = 0; i < m; i++) {
            final String[] s = br.readLine().split(" ");
            if (shortest[Integer.parseInt(s[0])][Integer.parseInt(s[1])] > Integer.parseInt(s[2])) {
                shortest[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = Integer.parseInt(s[2]);
            }
        }

        for (int i = 1; i <= n; i++) {
            shortest[i][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (shortest[j][k] > shortest[j][i] + shortest[i][k]) {
                        shortest[j][k] = shortest[j][i] + shortest[i][k];
                        middle[j][k] = i;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (shortest[i][j] == INF) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(shortest[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
        sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || shortest[i][j] == INF) {
                    sb.append(0).append(System.lineSeparator());
                    continue;
                }
                final List<Integer> path = findPath(i, j);
                path.add(0, i);
                path.add(j);
                path.add(0, path.size());
                sb.append(path.stream().map(String::valueOf).collect(Collectors.joining(" "))).append(System.lineSeparator());
            }
        }
        System.out.print(sb);
    }

    static List<Integer> findPath(int start, int end) {
        if (middle[start][end] == 0) {
            return new ArrayList<>();
        }
        final ArrayList<Integer> list = new ArrayList<>(findPath(start, middle[start][end]));
        list.add(middle[start][end]);
        list.addAll(findPath(middle[start][end], end));

        return list;
    }

}

class Node implements Comparable<Node> {

    int number;
    long weight;

    public Node(int number, long weight) {
        this.number = number;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.weight, o.weight);
    }
}
