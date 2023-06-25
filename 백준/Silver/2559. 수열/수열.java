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
        final int k = Integer.parseInt(s[1]);
        final int[] numbers = new int[n + 1];
        final AtomicInteger i = new AtomicInteger(1);
        Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .reduce(0, (left, right) -> {
                    final int current = i.getAndIncrement();
                    numbers[current] = left + right;
                    return numbers[current];
                });
        final int[] sums = new int[n - k + 1];

        for (int j = k; j <= n; j++) {
            sums[j - k] = numbers[j] - numbers[j - k];
        }
        final int max = Arrays.stream(sums).max().getAsInt();
        System.out.println(max);
    }
}

