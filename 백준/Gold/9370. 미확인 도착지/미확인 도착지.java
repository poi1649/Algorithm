
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static ArrayList<Node>[] graph;
    static int[] candidates;
    static int[] dist;
    static int n;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        final StringBuilder sb = new StringBuilder();

        for (int TIME = 0; TIME < T; TIME++) {
            final String[] s0 = br.readLine().split(" ");
            n = Integer.parseInt(s0[0]);
            final int m = Integer.parseInt(s0[1]);
            final int t = Integer.parseInt(s0[2]);
            dist = new int[n + 1];
            Arrays.fill(dist, (Integer.MAX_VALUE / 2) * 2);

            graph = new ArrayList[n + 1];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            final String[] s1 = br.readLine().split(" ");
            final int s = Integer.parseInt(s1[0]);
            final int g = Integer.parseInt(s1[1]);
            final int h = Integer.parseInt(s1[2]);

            for (int i = 0; i < m; i++) {
                final StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if ((a == g && b == h) || (a == h && b == g)) {
                    graph[a].add(new Node(b, w * 2 - 1));
                    graph[b].add(new Node(a, w * 2 - 1));
                } else {
                    graph[a].add(new Node(b, w * 2));
                    graph[b].add(new Node(a, w * 2));
                }
            }

            candidates = new int[t];
            for (int j = 0; j < t; j++) {
                final String string = br.readLine();
                candidates[j] = Integer.parseInt(string);
            }

            dijkstra(s);

            final ArrayList<Integer> result = new ArrayList<>();

            Arrays.sort(candidates);

            for (int candidate : candidates) {
                if (dist[candidate] % 2 == 1) {
                    result.add(candidate);
                }
            }
            final String results = result.stream().map(String::valueOf).collect(Collectors.joining(" "));
            sb.append(results).append(System.lineSeparator());
        } // 각 테스트 케이스
        System.out.print(sb);
        br.close();
    } // void main

    private static void dijkstra(int u) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[u] = 0;
        pq.add(new Node(u, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            if (visited[currentNode.number]) {
                continue;
            }
            visited[currentNode.number] = true;

            for (Node node : graph[currentNode.number]) {
                if (!visited[node.number] && dist[node.number] > dist[currentNode.number] + node.weight) {
                    dist[node.number] = dist[currentNode.number] + node.weight;
                    pq.add(new Node(node.number, dist[node.number]));
                }
            }
        }
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
        return weight - o.weight;
    }
}

