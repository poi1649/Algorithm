import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main {

    static int q, n, m, result;
    static List<Target> targets = new ArrayList<>();

    static class Target {

        int number;
        double time;

        public Target(final int number, final double time) {
            this.number = number;
            this.time = time;
        }
    }

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

        final var map = Arrays.stream(br.readLine().split(" "))
                              .map(Integer::parseInt)
                              .collect(Collectors.toMap(integer -> integer, integer -> 1, Integer::sum));
        int max = 0;
        for (final Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            max = Math.max(max, integerIntegerEntry.getValue());
        }
        System.out.println(max);
    }
}

