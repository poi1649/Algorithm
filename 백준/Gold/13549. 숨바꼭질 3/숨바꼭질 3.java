
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int[] shortestPath;
    static int n;
    static PriorityQueue<Edge> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int k = Integer.parseInt(s[1]);
        shortestPath = new int[100001];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        shortestPath[n] = 0;
        queue.add(new Edge(n, 0));
        while (!queue.isEmpty()) {
            check(queue.poll());
        }
        System.out.println(shortestPath[k]);
    }

    static void check(Edge target) {
        if (2 * target.nodeNumber <= 100000 && shortestPath[2 * target.nodeNumber] > shortestPath[target.nodeNumber]) {
            shortestPath[2 * target.nodeNumber] = shortestPath[target.nodeNumber];
            queue.add(new Edge(2 * target.nodeNumber, shortestPath[2 * target.nodeNumber]));
        }
        if (target.nodeNumber > 0 && shortestPath[target.nodeNumber - 1] > shortestPath[target.nodeNumber] + 1) {
            shortestPath[target.nodeNumber - 1] = shortestPath[target.nodeNumber] + 1;
            queue.add(new Edge(target.nodeNumber - 1, shortestPath[target.nodeNumber - 1]));
        }
        if (target.nodeNumber < 100000 && shortestPath[target.nodeNumber + 1] > shortestPath[target.nodeNumber] + 1) {
            shortestPath[target.nodeNumber + 1] = shortestPath[target.nodeNumber] + 1;
            queue.add(new Edge(target.nodeNumber + 1, shortestPath[target.nodeNumber + 1]));
        }
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

