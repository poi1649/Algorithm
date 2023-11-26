import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    static int t;

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

    public static void process(BufferedReader br, int p) throws IOException {
        final var split = br.readLine().split(" ");
        final var n = parseInt(split[0]);
        final var k = parseInt(split[1]);
        final var ans = Arrays.stream(br.readLine().split(" "))
                                  .map(Integer::parseInt)
                                  .sorted()
                                  .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(k - 1)));
        System.out.println(ans);
    }
}


