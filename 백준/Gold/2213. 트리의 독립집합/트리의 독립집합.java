import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        final String[] s = br.readLine().split(" ");
        weight = new int[n + 1];
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            weight[i + 1] = parseInt(s[i]);
        }
        graph = new ArrayList[n + 1];
        results = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i <= n; i++) {
            results[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int a = parseInt(s1[0]);
            final int b = parseInt(s1[1]);

            graph[a].add(b);
            graph[b].add(a);
        }
        int result = findMax(1, 0);
        System.out.println(result);
        System.out.println(results[1].stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int findMax(int target, int parent) {
        if (dp[target] != -1) {
            return dp[target];
        }

        int resultOne = 0;
        int resultTwo = weight[target];
        final ArrayList<Integer> childs = graph[target];
        final int size = childs.size();

        for (int i = 0; i < size; i++) {
            if (childs.get(i) == parent) {
                continue;
            }
            resultOne += findMax(childs.get(i), target);
            final ArrayList<Integer> grandChilds = graph[childs.get(i)];
            final int size1 = grandChilds.size();
            for (int j = 0; j < size1; j++) {
                if (grandChilds.get(j) == target) {
                    continue;
                }
                resultTwo += findMax(grandChilds.get(j), childs.get(i));
            }
        }
        if (resultOne < resultTwo) {
            results[target].add(target);
            for (int i = 0; i < size; i++) {
                if (childs.get(i) == parent) {
                    continue;
                }
                final ArrayList<Integer> grandChilds = graph[childs.get(i)];
                final int size1 = grandChilds.size();
                for (int j = 0; j < size1; j++) {
                    if (grandChilds.get(j) == target) {
                        continue;
                    }
                    results[target].addAll(results[grandChilds.get(j)]);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (childs.get(i) == parent) {
                    continue;
                }
                results[target].addAll(results[childs.get(i)]);
            }
        }
        dp[target] = Math.max(resultOne, resultTwo);
        return dp[target];
    }
}

