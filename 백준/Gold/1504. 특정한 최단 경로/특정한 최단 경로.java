
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int[] shortestPath;
    static PriorityQueue<Edge> queue = new PriorityQueue<>();
    static ArrayList<Edge>[] list;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int e = Integer.parseInt(s[1]);
        list = new ArrayList[n + 1];
        shortestPath = new int[n + 1];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        for (int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int u = Integer.parseInt(s1[0]);
            final int v = Integer.parseInt(s1[1]);
            final int w = Integer.parseInt(s1[2]);
            list[u].add(new Edge(v, w));
            list[v].add(new Edge(u, w));
        }
        final String[] s1 = br.readLine().split(" ");
        final int e1 = Integer.parseInt(s1[0]);
        final int e2 = Integer.parseInt(s1[1]);

        queue.add(new Edge(1, 0));
        shortestPath[1] = 0;
        while (!queue.isEmpty()) {
            check(queue.poll());
        }
        int oneToE1 = shortestPath[e1];
        int oneToE2 = shortestPath[e2];
        if (oneToE1 == Integer.MAX_VALUE || oneToE2 == Integer.MAX_VALUE || shortestPath[n] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[e2] = 0;
        queue.add(new Edge(e2, 0));
        while (!queue.isEmpty()) {
            check(queue.poll());
        }
        final int e2ToN = shortestPath[n];
        final int e2Toe1 = shortestPath[e1];

        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[e1] = 0;
        queue.add(new Edge(e1, 0));
        while (!queue.isEmpty()) {
            check(queue.poll());
        }

        final int e1ToN = shortestPath[n];
        final int e1Toe2 = shortestPath[e2];

        int rootOne = oneToE1 + e1Toe2 + e2ToN;
        int rootTwo = oneToE2 + e2Toe1 + e1ToN;

        System.out.println(Math.min(Math.abs(rootOne), Math.abs(rootTwo)));

    }

    static void check(Edge source) {
        list[source.nodeNumber].forEach(dest -> {
            if (shortestPath[dest.nodeNumber] > shortestPath[source.nodeNumber] + dest.value) {
                shortestPath[dest.nodeNumber] = shortestPath[source.nodeNumber] + dest.value;
                queue.add(dest);
            }
        });
    }
}

class Edge implements Comparable<Edge> {
    int nodeNumber;
    int value;

    Edge(int nodeNumber, int value) {
        this.nodeNumber = nodeNumber;
        this.value = value;
    }

    @Override
    public int compareTo(Edge o) {
        return value - o.value;
    }
}

