import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();
    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int[] distance;
    static int longest = 0;
    static int longestIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        distance = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            final String[] s = br.readLine().split(" ");
            for (int j = 1; j < s.length - 1; j += 2) {
                list[Integer.parseInt(s[0])].add(new Node(
                        Integer.parseInt(s[j]),
                        Integer.parseInt(s[j + 1])
                ));
            }
        }

        deque.add(1);
        visited[1] = true;

        while (!deque.isEmpty()) {
            check(deque.pollFirst());
        }

        deque.add(longestIndex);
        Arrays.fill(visited, false);
        Arrays.fill(distance, 0);
        visited[longestIndex] = true;
        longest = 0;

        while(!deque.isEmpty()) {
            check(deque.pollFirst());
        }
        System.out.println(longest);
    }

    static void check(int target) {
        list[target].forEach(e -> {
            if (visited[e.number]) {
                return;
            }
            visited[e.number] = true;
            distance[e.number] = distance[target] + e.weight;
            if (distance[e.number] > longest)  {
                longest = distance[e.number];
                longestIndex = e.number;
            }
            deque.add(e.number);
        });
    }
}

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}


