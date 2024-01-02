import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int q, n, k, m, result;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;

        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int c) throws IOException {
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        m = parseInt(split[1]);
        final var set = new DisjointSet(n);
        for (int i = 0; i < m; i++) {
            final var split1 = br.readLine().split(" ");
            set.union(parseInt(split1[0]), parseInt(split1[1]));
        }
        final var split1 = br.readLine().split(" ");
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int cur = parseInt(split1[i]);
            int next = parseInt(split1[i + 1]);
            if (set.find(cur) != set.find(next)) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}

class DisjointSet {

    int[] ranks;
    int[] parents;

    public DisjointSet(int size) {
        ranks = new int[size + 1];
        parents = new int[size + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public int find(int cur) {
        if (cur != parents[cur]) {
            parents[cur] = find(parents[cur]);
        }
        return parents[cur];
    }

    public void union(int one, int two) {
        int xp = find(one);
        int yp = find(two);

        if (xp == yp) {
            return;
        }

        if (ranks[xp] < ranks[yp]) {
            int tmp = xp;
            xp = yp;
            yp = tmp;
        }

        parents[yp] = xp;
        if (ranks[xp] == ranks[yp]) {
            ranks[xp]++;
        }
    }
}
