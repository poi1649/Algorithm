import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator.OfPrimitive;
import java.util.stream.Collectors;

public class Main {

    static int q, n, k, m, result;
    static int[] mods = new int[100001];
    static long MOD = 1_000_000_007;

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
        final var split = br.readLine().split(" ");
        m = parseInt(split[0]);
        k = parseInt(split[1]);
        int target = m * k;
        final var collect = Arrays.stream(br.readLine().split(" "))
                                  .map(Integer::parseInt)
                                  .sorted(Comparator.reverseOrder())
                                  .collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            target -= collect.get(i);
            if (target <=0) {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println("STRESS");
    }
}

