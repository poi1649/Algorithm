import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();
    static ArrayList<Integer>[] list;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final String[] s = br.readLine().split(" ");
            final int i1 = Integer.parseInt(s[0]);
            final int i2 = Integer.parseInt(s[1]);
            list[i1].add(i2);
            list[i2].add(i1);
        }

        deque.add(1);
        while (!deque.isEmpty()) {
            check(deque.pollFirst());
        }

        for (int i = 2; i <= n; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void check(int target) {
        list[target].forEach(e -> {
            if (parents[e] > 0) {
                return;
            }
            parents[e] = target;
            deque.add(e);
        });
    }
}


