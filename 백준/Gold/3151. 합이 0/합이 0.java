import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    static int[] arr = new int[20001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());
        final var inputs = Arrays.stream(br.readLine().split(" "))
                                 .map(Integer::parseInt)
                                 .sorted()
                                 .collect(Collectors.toList());
        inputs.forEach(i -> arr[i + 10000]++);
        long ans = 0;
        int[] temp = new int[20001];

        final var size = inputs.size();
        for (int i = 0; i < size; i++) {
            final var i1 = inputs.get(i);
            System.arraycopy(arr, 0, temp, 0, 20001);
            for (int j = 0; j <= i; j++) {
                temp[inputs.get(j) + 10000]--;
            }
            for (int j = i + 1; j < size; j++) {
                final var i2 = inputs.get(j);
                final var targetTwo = i2 + 10000;
                temp[targetTwo]--;
                final var sum = i1 + i2;
                if (i2 <= (-sum) && (-sum) <= 10000) {
                    ans += temp[(-sum) + 10000];
                }
            }
        }
        System.out.println(ans);
    }
}
