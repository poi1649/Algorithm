import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int n, m, x;
    static int[][] map;
    static final PriorityQueue<Node> deque = new PriorityQueue<>();
    static final Deque<Node2> deque2 = new LinkedList<>();
    static boolean[][] visited;
    static DisjointSet disjointSet;
    static StringBuilder sb = new StringBuilder();
    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            final String[] s1 = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = parseInt(s1[j - 1]);
            }
        }

        disjointSet = new DisjointSet(n * m);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                for (int k = i + 1; k <= n; k++) {
                    if (map[k][j] == 0) {
                        continue;
                    }
                    if (k - i  < 3) {
                        break;
                    }
                    deque.add(
                            new Node(
                                    (i - 1) * m + j,
                                    (k - 1) * m + j,
                                    Math.abs(k - i - 1)));
                    break;
                }
                for (int k = j + 1; k <= m; k++) {
                    if (map[i][k] == 0) {
                        continue;
                    }
                    if (k - j  < 3) {
                        break;
                    }
                    deque.add(
                            new Node(
                                    (i - 1) * m + j,
                                    (i - 1) * m + k,
                                    Math.abs(k - j - 1)));
                    break;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                deque2.add(new Node2(i, j));
            }
        }

        while (!deque2.isEmpty()) {
            final Node2 target = deque2.poll();
            final int x = target.x;
            final int y = target.y;
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            check(x,y);
        }

        int count = 0;

        while (!deque.isEmpty()) {
            final Node target = deque.poll();
            final int src = target.src;
            final int dest = target.dest;
            final int weight = target.weight;
            if (disjointSet.isSameSet(src, dest)) {
                continue;
            }
            disjointSet.union(src, dest);
            count += weight;
        }

        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1) {
                    continue;
                }
                final int first = (i - 1) * m + j;
                flag = true;
                for (int k = 1; k <= n; k++) {
                    for (int l = 1; l <= m; l++) {
                        if (map[k][l] == 1) {
                            continue;
                        }
                        final int target = (k - 1) * m + l;
                        if (disjointSet.isSameSet(first, target)) {
                            continue;
                        }
                        disjointSet.union(first, target);
                    }
                }
                break;
            }
            if (flag) {
                break;
            }
        }

        final HashSet<Integer> integers = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                final int root = disjointSet.findSet((i - 1) * m + j);
                integers.add(root);
            }
        }

        if (integers.size() > 2) {
            System.out.println(-1);
            return;
        }
        System.out.println(count);
    }

    static void check(int x, int y) {
        vectors.forEach(ints -> {
            final int nx = ints[0] + x;
            final int ny = ints[1] + y;
            if (nx < 1 || nx > n || ny < 1 || ny > m) {
                return;
            }
            if (map[nx][ny] == 0) {
                return;
            }


            if (visited[nx][ny]) {
                return;
            }

            final int i = (nx - 1) * m + ny;
            final int j = (x - 1) * m + y;
            disjointSet.union(i, j);
            visited[nx][ny] = true;
            check(nx, ny);
        });
    }
}

class Node2 {

    int x;
    int y;

    public Node2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


class Node implements Comparable<Node> {

    int src;
    int dest;
    int weight;

    public Node(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

class DisjointSet {

    private final int[] parents;
    private final int[] rank;
    private final int[] size;

    public DisjointSet(int n) {
        parents = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int findSet(int x) {
        if (x != parents[x]) {
            parents[x] = findSet(parents[x]);
        }
        return parents[x];
    }

    public boolean isSameSet(int x, int l) {
        return findSet(l) == findSet(x);
    }

    public int getSize(int x) {
        return size[x];
    }

    public int union(int x, int l) {
        final int xRoot = findSet(x);
        final int yRoot = findSet(l);

        if (xRoot == yRoot) {
            return xRoot;
        }

        if (rank[xRoot] > rank[yRoot]) {
            parents[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
            return xRoot;
        }
        if (rank[xRoot] == rank[yRoot]) {
            rank[yRoot]++;
        }
        parents[xRoot] = yRoot;
        size[yRoot] += size[xRoot];
        return yRoot;
    }
}

