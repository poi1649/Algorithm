import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static int t;
    static int m;
    static int n;
    static List<Integer>[] g = new List[51];
    static List<Integer> inputs = new ArrayList<>();
    static boolean[] chk = new boolean[51];
    static boolean[] visited = new boolean[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        final var s1 = br.readLine().split(" ");
        final var k = parseInt(s1[0]);
        for (int i = 1; i <= k; i++) {
            chk[parseInt(s1[i])] = true;
        }
        int ans = 0;
        for (int i = 1; i <= 50; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            final var s2 = br.readLine().split(" ");
            final var z = parseInt(s2[0]);
            inputs.add(parseInt(s2[1]));
            for (int j = 1; j <= z; j++) {
                int c = parseInt(s2[j]);
                for (int l = j + 1; l <= z; l++) {
                    int d = parseInt(s2[l]);
                    g[c].add(d);
                    g[d].add(c);
                }
            }
        }
        Deque<Integer> q = new LinkedList<>();
        for (int input : inputs) {
            q.add(input);
            visited[input] = true;
            boolean flag = true;
            while (!q.isEmpty()) {
                final var i = q.pollFirst();
                if (chk[i]) {
                    flag = false;
                    break;
                }
                for (int v : g[i]) {
                    if (!visited[v]) {
                        q.add(v);
                        visited[v] = true;
                    }
                }
            }
            q.clear();
            Arrays.fill(visited, false);
            if (flag) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}

