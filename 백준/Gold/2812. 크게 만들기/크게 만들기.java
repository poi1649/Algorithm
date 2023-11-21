import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int t;
    static int m;
    static int n;
    static int a;
    static int b;
    static StringBuilder sb = new StringBuilder();

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
        final var arr = br.readLine().toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        for (char c : arr) {
            int curr = c - '0';
            while (!stack.isEmpty() && stack.peekLast() < curr && m > 0) {
                stack.pollLast();
                m--;
            }
            stack.addLast(curr);
        }
        while (m > 0) {
            stack.pollLast();
            m--;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        System.out.println(sb);
    }
}


