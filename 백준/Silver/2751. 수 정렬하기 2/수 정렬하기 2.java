import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int t;
    static int n;
    static int m;
    static int b;
    static Set<String> list = new HashSet<>();
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
        final var n = parseInt(br.readLine());
        final var ints = new int[n];
        for (int i = 0; i < n; i++) {
            final var i1 = parseInt(br.readLine());
            ints[i] = i1;
        }
        Arrays.sort(ints);
        for (final int anInt : ints) {
            sb.append(anInt).append("\n");
        }
        System.out.print(sb);
    }
}


