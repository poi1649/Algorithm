import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] maxLength = new int[n];
        Arrays.fill(maxLength, 1);

        maxLength[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    maxLength[i] = Math.max(maxLength[j] + 1, maxLength[i]);
                }
            }
        }

        int result = Arrays.stream(maxLength).max().getAsInt();
        System.out.println(result);
    }
}

