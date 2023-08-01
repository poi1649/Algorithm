import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int n, m, x;
    static int[] shortestToNode;
    static int[] shortestToX;
    static ArrayList<Node>[] map;
    static ArrayList<Node>[] reverseMap;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        x = parseInt(s[2]);
        map = new ArrayList[n + 1];
        reverseMap = new ArrayList[n + 1];
        shortestToNode = new int[n + 1];
        shortestToX = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
            reverseMap[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int a = parseInt(s1[0]);
            final int b = parseInt(s1[1]);
            final int w = parseInt(s1[2]);
            map[a].add(new Node(b, w));
            reverseMap[b].add(new Node(a, w));
        }

        queue.add(new Node(x, 0));
        Arrays.fill(shortestToNode, Integer.MAX_VALUE);
        shortestToNode[x] = 0;
        while (!queue.isEmpty()) {
            check(queue.poll(), map, shortestToNode);
        }

        queue.add(new Node(x, 0));
        Arrays.fill(shortestToX, Integer.MAX_VALUE);
        shortestToX[x] = 0;
        while (!queue.isEmpty()) {
            check(queue.poll(), reverseMap, shortestToX);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, shortestToNode[i] + shortestToX[i]);
        }
        System.out.println(max);
    }

    static void check(Node target, ArrayList<Node>[] graph, int[] distance) {
        if (target.weight > distance[target.number]) {
            return;
        }
        graph[target.number].forEach(node -> {
                    if (distance[node.number] > distance[target.number] + node.weight) {
                        distance[node.number] = distance[target.number] + node.weight;
                        queue.add(new Node(node.number, distance[node.number]));
                    }
                }
        );
    }
}

class Node implements Comparable<Node> {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return (this.weight - o.weight);
    }
}
