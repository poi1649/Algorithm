import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        final List<Integer> collect = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toUnmodifiableList());
        int x = collect.stream().max(Integer::compareTo).orElse(1);
        boolean[] primes = new boolean[x + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        int count = 0;
        for (int j = 2; j <= x; j++) {
            if (primes[j]) {
                for (int i = j * 2; i <= x; i += j) {
                    primes[i] = false;
                }
            }
        }
        for (int s : collect) {
            if (primes[s]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
