import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int t;
    static int m;
    static int n;
    static boolean[] prime = new boolean[10001];
    static boolean[] checked = new boolean[10001];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = parseInt(br.readLine());
//        t = 1;
        int cur = 1;
        for (int i = 2; i <= 10000; i++) {
            if (checked[i]) {
                continue;
            }
            prime[i] = true;
            checked[i] = true;
            for (int j = i; j <= 10000; j = j + i) {
                checked[j] = true;
            }
        }
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        for (int i = n / 2; i >= 2; i--) {
            if (prime[i] && prime[n - i]) {
                if (n - i > i) {
                    System.out.println(i + " " + (n - i));
                    break;
                }
                System.out.println((n - i) + " " + i);
                break;
            }
        }
    }
}

