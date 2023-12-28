import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    static int q, n, k, m, result;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();

    static int[] arr = new int[10001];

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

    public static void process(BufferedReader br, int cur) throws IOException {
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        k = parseInt(split[1]);

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        final var split1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            map1.merge(parseInt(split1[i]), 1, Integer::sum);
            map2.merge(parseInt(split1[n + i]), 1, Integer::sum);
        }
        long ans = 0;
        for (final Entry<Integer, Integer> entry : map1.entrySet()) {
            final var i = map2.getOrDefault(entry.getKey(), 0);
            ans += (long) entry.getValue() * (n - i);
        }
        System.out.println(ans);
    }
}

