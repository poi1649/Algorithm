import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;

public class Main {

    static int t;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = parseInt(br.readLine());
//        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int t) throws IOException {
        final var n = parseInt(br.readLine());
        int[] entried = new int[201];
        int[] fifth = new int[201];
        int[] scores = new int[201];
        int[] scored = new int[201];

        final var s = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            final var team = parseInt(s[i - 1]);
            entried[team]++;
            if (entried[team] == 5) {
                fifth[team] = i;
            }
        }

        int score = 1;
        for (int i = 1; i <= n; i++) {
            final var team = parseInt(s[i - 1]);
            if (entried[team] == 6) {
                if (scored[team] < 4) {
                    scores[team] += score;
                    scored[team]++;
                }
                score++;
            }
        }

        int min = Integer.MAX_VALUE;
        int win = 0;
        for (int i = 1; i <= 200; i++) {
            if (scores[i] == 0) {
                continue;
            }

            if (scores[i] < min) {
                min = scores[i];
                win = i;
                continue;
            }

            if (scores[i] == min) {
                if (fifth[i] < fifth[win]) {
                    win = i;
                }
            }
        }
        System.out.println(win);
    }
}


