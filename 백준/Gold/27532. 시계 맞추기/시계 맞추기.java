import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int[] input = new int[1501];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            final var split = br.readLine().split(":");
            input[i] = ((parseInt(split[0]) * 60) + parseInt(split[1])) - 60;
        }
        int min = Integer.MAX_VALUE;

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= 720; i++) {
            set.clear();
            set.add(0);
            for (int j = 2; j <= n; j++) {
                var expected = (input[1] + ((j - 1) * i)) % 720;
                var diff = input[j] - expected;
                if (diff < 0) {
                    diff += 720;
                }
                set.add(diff);
            }
            min = Math.min(min, set.size());
        }
        System.out.println(min);
    }
}
