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
        n = parseInt(br.readLine());
        final var charArray = br.readLine().toCharArray();
        Deque<Character> deque = new LinkedList<>();
        int ans = 0;
        for (char c : charArray) {
            ans = Math.max(ans, deque.size());
            if (deque.isEmpty()) {
                deque.add(c);
                continue;
            }
            if (c == '(') {
                if (deque.peekLast() == ')') {
                    deque.pollLast();
                    continue;
                }
                deque.addLast(c);
                continue;
            }

            if (deque.peekLast() == '(') {
                deque.pollLast();
                continue;
            }
            deque.addLast(c);
        }
        if (deque.isEmpty()) {
            System.out.println(ans);
            return;
        }
        System.out.println(-1);
    }
}

