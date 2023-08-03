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
        weight = new int[n + 1];
        Arrays.fill(dp, -1);
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        final String[] s1 = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            weight[i] = parseInt(s1[i - 1]);
        }
        for (int i = 0; i < n - 1; i++) {
            final String[] s = br.readLine().split(" ");
            final int a = parseInt(s[0]);
            final int b = parseInt(s[1]);
            graph[a].add(b);
            graph[b].add(a);
        }
        final int result = findMax(1, -1);
        System.out.println(result);
    }

    static int findMax(int target, int parent) {
        if (dp[target] != -1) {
            return dp[target];
        }

        final ArrayList<Integer> childs = graph[target];
        int sumWithTarget = weight[target];
        int sumWithoutTarget = 0;

        for (int child : childs) {
            if (child == parent) {
                continue;
            }

            sumWithoutTarget += findMax(child, target);

            for (int grandChild : graph[child]) {
                if (grandChild == target) {
                    continue;
                }
                sumWithTarget += findMax(grandChild, child);
            }
        }

        return dp[target] = Math.max(sumWithTarget, sumWithoutTarget);
    }
}

