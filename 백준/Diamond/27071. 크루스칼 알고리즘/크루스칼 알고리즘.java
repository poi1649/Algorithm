import java.util.*;
import java.io.*;

public class Main {
    static final long MOD = 998244353;
    static int N;
    static List<List<Long>> L = new ArrayList<>();

    static class UnionFind {
        private int[] parent, size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int root(int x) {
            return x == parent[x] ? x : (parent[x] = root(parent[x]));
        }

        boolean same(int x, int y) {
            return root(x) == root(y);
        }

        boolean unite(int x, int y) {
            x = root(x);
            y = root(y);
            if (x == y) return false;
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            return true;
        }

        int size(int x) {
            return size[root(x)];
        }
    }

    static class Edge implements Comparable<Edge> {
        int weight, u, v;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static long powMod(long base, long exponent) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent /= 2;
        }
        return result;
    }

    static long computeDeterminant() {
        long result = 1;
        for (int i = 1; i < N; i++) {
            long inv = powMod(L.get(i).get(i), MOD - 2);
            for (int j = i + 1; j < N; j++) {
                if (L.get(j).get(i) == 0) continue;
                long ratio = (L.get(j).get(i) * inv) % MOD;
                for (int k = i; k < N; k++) {
                    L.get(j).set(k, (L.get(j).get(k) - ratio * L.get(i).get(k)) % MOD);
                }
            }
            result = (result * L.get(i).get(i)) % MOD;
        }
        return (result + MOD) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }
        Collections.sort(edges);

        UnionFind uf = new UnionFind(N + 1);

        for (int i = 0; i <= N; i++) {
            L.add(new ArrayList<>(Collections.nCopies(N + 1, 0L)));
        }

        long answer = 1;
        for (int i = 0, j = 0; i < M; i = j) {
            int currentWeight = edges.get(i).weight;
            while (j < M && edges.get(j).weight == currentWeight) {
                j++;
            }
            for (int k = i; k < j; k++) {
                Edge edge = edges.get(k);
                if (uf.same(edge.u, edge.v)) continue;

                int uRoot = uf.root(edge.u);
                int vRoot = uf.root(edge.v);
                L.get(uRoot).set(uRoot, L.get(uRoot).get(uRoot) + 1);
                L.get(vRoot).set(vRoot, L.get(vRoot).get(vRoot) + 1);
                L.get(uRoot).set(vRoot, L.get(uRoot).get(vRoot) - 1);
                L.get(vRoot).set(uRoot, L.get(vRoot).get(uRoot) - 1);
            }
            int unionCount = 0;
            for (int k = i; k < j; k++) {
                Edge edge = edges.get(k);
                if (uf.unite(edge.u, edge.v)) unionCount++;
            }
            while (unionCount > 0) {
                answer = (answer * unionCount) % MOD;
                unionCount--;
            }
        }

        answer = (answer * computeDeterminant()) % MOD;
        System.out.println(answer);
    }
}
