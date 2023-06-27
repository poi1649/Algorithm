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

        int[][] sums = new int[n][n];

        for (int i = 0; i < n; i++) {
            final AtomicInteger j = new AtomicInteger(0);
            final int finalI = i;
            Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .reduce(0, (left, right) -> {
                                final int result = left + right;
                                sums[finalI][j.getAndIncrement()] = result;
                                return result;
                            }
                    );
        }

        final StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int x1 = Integer.parseInt(s1[0]) - 1;
            final int y1 = Integer.parseInt(s1[1]) - 1;
            final int x2 = Integer.parseInt(s1[2]) - 1;
            final int y2 = Integer.parseInt(s1[3]) - 1;
            int temp = 0;
            for (int j = x1; j <= x2; j++) {
                if (y1 == 0) {
                    temp += sums[j][y2];
                    continue;
                }
                temp += sums[j][y2] - sums[j][y1 - 1];
            }
            sb.append(temp).append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}
