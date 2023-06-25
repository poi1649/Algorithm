import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);

        int[] numbers = new int[n + 1];
        AtomicInteger i = new AtomicInteger(1);

        Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .reduce(0, (left, right) -> {
                    numbers[i.getAndIncrement()] = left + right;
                    return left + right;
                });
        final StringBuilder sb = new StringBuilder();

        for (int j = 0; j < m; j++) {
            final String[] s1 = br.readLine().split(" ");
            final int i1 = Integer.parseInt(s1[0]);
            final int i2 = Integer.parseInt(s1[1]);
            final int result = numbers[i2] - numbers[i1 - 1];
            sb.append(result).append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}

