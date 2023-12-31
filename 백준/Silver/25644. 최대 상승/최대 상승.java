import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

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

    public static void process(BufferedReader br, int c) throws IOException {
        n = parseInt(br.readLine());
        final var collect = Arrays.stream(br.readLine().split(" "))
                                  .map(Integer::parseInt)
                                  .collect(Collectors.toList());

        int min = collect.get(0);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int cur = collect.get(i);
            min = Math.min(cur, min);
            int tmp = cur - min;
            ans = Math.max(tmp, ans);
        }
        System.out.println(ans);
    }
}

