
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
    static int[][] candidates;
    static int[] dist;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        final StringBuilder sb = new StringBuilder();

        for (int TIME = 0; TIME < T; TIME++) {
            final String[] s0 = br.readLine().split(" ");
            final int n = Integer.parseInt(s0[0]);
            final int m = Integer.parseInt(s0[1]);
            final int t = Integer.parseInt(s0[2]);

            final String[] s1 = br.readLine().split(" ");
            final int s = Integer.parseInt(s1[0]);
            final int g = Integer.parseInt(s1[1]);
            final int h = Integer.parseInt(s1[2]);

            dist = new int[n + 1];
            graph = new ArrayList[n + 1];
            candidates = new int[t][4];
            Arrays.fill(dist, (Integer.MAX_VALUE / 2) * 2);

            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                final StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[a].add(new Node(b, w));
                graph[b].add(new Node(a, w));
            }

            for (int j = 0; j < t; j++) {
                candidates[j][0] = Integer.parseInt(br.readLine());
            }

            dist[s] = 0;
            queue.add(new Node(s, 0));
            while (!queue.isEmpty()) {
                dijkstra2(queue.poll(), dist);
            }
//            dijkstra(s, dist);

            int sTog = dist[g];
            int sToh = dist[h];
            int gToh;
            for (int j = 0; j < t; j++) {
                candidates[j][1] = dist[candidates[j][0]];
            }

            Arrays.fill(dist, (Integer.MAX_VALUE / 2) * 2);
            dist[g] = 0;
            queue.add(new Node(g, 0));
            while (!queue.isEmpty()) {
                dijkstra2(queue.poll(), dist);
            }
//            dijkstra(g, dist);

            gToh = dist[h];

            for (int j = 0; j < t; j++) {
                candidates[j][2] = dist[candidates[j][0]];
            }

            Arrays.fill(dist, (Integer.MAX_VALUE / 2) * 2);
            dist[h] = 0;
            queue.add(new Node(h, 0));
            while (!queue.isEmpty()) {
                dijkstra2(queue.poll(), dist);
            }

//            dijkstra(h, dist);
            for (int j = 0; j < t; j++) {
                candidates[j][3] = dist[candidates[j][0]];
            }

            final ArrayList<Integer> result = new ArrayList<>();

            for (int j = 0; j < t; j++) {
                if ((
                        candidates[j][1]  // s -> x
                                == sTog  // s -> m
                                + gToh // m -> m1
                                + candidates[j][3] // m1 -> x
                                ||
                                candidates[j][1]  // s -> x
                                        == sToh  // s -> m
                                        + gToh // m -> m1
                                        + candidates[j][2] // m1 -> x
                )
                ) {
                    result.add(candidates[j][0]);
                }
            }

            result.sort(Integer::compareTo);

            final String results = result.stream().map(String::valueOf).collect(Collectors.joining(" "));
            sb.append(results).append(System.lineSeparator());
        } // 각 테스트 케이스
        System.out.print(sb);
        br.close();
    } // void main

    private static void dijkstra(int u, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[u] = 0;
        pq.add(new Node(u, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            for (Node node : graph[currentNode.number]) {
                if (dist[node.number] > dist[currentNode.number] + node.weight) {
                    dist[node.number] = dist[currentNode.number] + node.weight;
                    pq.add(new Node(node.number, dist[node.number]));
                }
            }
        }
    }

    private static void dijkstra2(Node target, int[] dist) {

        for (Node node : graph[target.number]) {
            if (dist[node.number] > dist[target.number] + node.weight) {
                dist[node.number] = dist[target.number] + node.weight;
                queue.add(new Node(node.number, dist[node.number]));
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

