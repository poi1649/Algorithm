import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int mod = 1000003;
    private static int[] parents = new int[200005];
    private static final List<Node> rollbackList = new ArrayList<>();
    private static final List<Edge> sameWeightEdges = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Arrays.fill(parents, -1);

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Edge> edge = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            edge.add(new Edge(a, b, w));
        }

        edge.sort((o1, o2) -> o2.weight - o1.weight);

        long ans = 1;
        while (!edge.isEmpty()) {
            sameWeightEdges.clear();
            sameWeightEdges.add(edge.remove(edge.size() - 1));
            while (!edge.isEmpty() && edge.get(edge.size() - 1).weight == sameWeightEdges.get(0).weight) {
                sameWeightEdges.add(edge.remove(edge.size() - 1));
            }
            ans *= solve();
            ans %= mod;
        }
        System.out.println(ans);
    }

    private static int f(int a) {
        if (parents[a] < 0) {
            return a;
        }
        return f(parents[a]);
    }

    private static boolean merge(int a, int b) {
        a = f(a);
        b = f(b);
        if (a != b) {
            if (-parents[a] > -parents[b]) {
                parents[a] += parents[b];
                rollbackList.add(new Node(b, a, parents[b]));
                parents[b] = a;
            } else {
                parents[b] += parents[a];
                rollbackList.add(new Node(a, b, parents[a]));
                parents[a] = b;
            }
            return true;
        }
        return false;
    }

    private static void rollBack() {
        Node last = rollbackList.remove(rollbackList.size() - 1);
        int a = last.a;
        int b = last.b;
        int parent = last.parent;
        parents[b] -= parent;
        parents[a] = parent;
    }

    private static int solve() {
        int diff = 0;
        for (Edge edge : sameWeightEdges) {
            diff += merge(edge.from, edge.to) ? 1 : 0;
        }
        for (int i = 0; i < diff; i++) {
            rollBack();
        }
        int result = 0;
        for (int i = 0; i < (1 << sameWeightEdges.size()); i++) {
            if (Integer.bitCount(i) != diff) {
                continue;
            }
            int cnt = 0;
            for (int j = 0; j < sameWeightEdges.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    cnt += merge(sameWeightEdges.get(j).from, sameWeightEdges.get(j).to) ? 1 : 0;
                }
            }
            result += (cnt == diff) ? 1 : 0;
            for (int j = 0; j < cnt; j++) {
                rollBack();
            }
        }
        for (Edge edge : sameWeightEdges) {
            merge(edge.from, edge.to);
        }
        return result;
    }
}

class Edge {

    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Node {
    int a;
    int b; 
    int parent;
    
    Node(int a, int b, int parent) {
        this.a = a;
        this.b = b;
        this.parent = parent;
    }
}