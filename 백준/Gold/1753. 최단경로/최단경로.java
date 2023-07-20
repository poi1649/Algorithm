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
        final int vs = Integer.parseInt(s[0]);
        list = new ArrayList[vs + 1];
        for (int i = 1; i <= vs; i++) {
            list[i] = new ArrayList<>();
        }
        shortestPath = new int[vs + 1];
        final int e = Integer.parseInt(s[1]);
        final int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < e; i++) {
            final String[] s1 = br.readLine().split(" ");
            list[Integer.parseInt(s1[0])].add(new Edge(Integer.parseInt(s1[1]), Integer.parseInt(s1[2])));
        }

        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[start] = 0;
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            check(queue.poll());
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < shortestPath.length; i++) {
            if (shortestPath[i] == Integer.MAX_VALUE) {
                sb.append("INF").append(System.lineSeparator());
                continue;
            }
            sb.append(shortestPath[i]).append(System.lineSeparator());
        }

        System.out.print(sb);
    }

    static void check(Edge target) {
        final ArrayList<Edge> edges = list[target.end];
        edges.forEach(next -> {
            if (shortestPath[next.end] > shortestPath[target.end] + next.value) {
                shortestPath[next.end] = shortestPath[target.end] + next.value;
                queue.add(new Edge(next.end, shortestPath[next.end]));
            }
        });
    }
}

class Edge implements Comparable<Edge> {
    int end;
    int value;

    Edge(int end, int value) {
        this.end = end;
        this.value = value;
    }

    @Override
    public int compareTo(Edge o) {
        return value - o.value;
    }
}

