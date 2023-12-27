import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

        while (true) {
            if (!process(br, cur++)) {
                break;
            }
            sb.append(System.lineSeparator());
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean process(BufferedReader br, int cur) throws IOException {
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        if (n == 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(parseInt(split[i]));
        }
        check(0, 0, list);
        deque.clear();
        return true;
    }

    public static void check(int depth, int start, List<Integer> list) {
        if (depth == 6) {
            for (final Integer i : deque) {
                sb.append(i).append(" ");
            }
            sb.append(System.lineSeparator());
            return;
        }


        for (int i = start; i < n; i++) {
            if (depth == 0 && (n - i) < 5) {
                return;
            }
            deque.addLast(list.get(i));
            check(depth + 1, i + 1, list);
            deque.pollLast();
        }
    }
}

