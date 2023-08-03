import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int n, r, q;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] results;
    static int[] dp;
    static int[] weight;

    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            final String[] s = br.readLine().split(" ");
            final int a = parseInt(s[0]);
            final int b = parseInt(s[1]);
            graph[a].add(b);
            graph[b].add(a);
        }
        final int result = findMin(1, -1);
        System.out.println(result);
    }

    static int findMin(int target, int parent) {
        if (dp[target] != -1) {
            return dp[target];
        }

        final ArrayList<Integer> list = graph[target];
        int minWithThis = 1;
        int minWithoutThis = 0;
        for (final int child : list) {
            if (child == parent) {
                continue;
            }

            minWithThis += findMin(child, target);
            minWithoutThis++;

            final ArrayList<Integer> grandChilds = graph[child];

            for (final int grandChild : grandChilds) {
                if (grandChild == target) {
                    continue;
                }
                minWithoutThis += findMin(grandChild, child);
            }
        }
        dp[target] = Math.min(minWithThis, minWithoutThis);

        return dp[target];
    }
}

