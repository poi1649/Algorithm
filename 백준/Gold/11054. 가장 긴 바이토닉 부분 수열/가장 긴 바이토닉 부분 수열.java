import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] increaseMaxLength = new int[n];
        int[] decreaseMaxLength = new int[n];
        Arrays.fill(increaseMaxLength, 1);
        Arrays.fill(decreaseMaxLength, 1);

        increaseMaxLength[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    increaseMaxLength[i] = Math.max(increaseMaxLength[j] + 1, increaseMaxLength[i]);
                }
                if (arr[j] > arr[i]) {
                    decreaseMaxLength[i] = Math.max(Math.max(increaseMaxLength[j], decreaseMaxLength[j]) + 1, decreaseMaxLength[i]);
                }
            }
        }

        int result = Arrays.stream(increaseMaxLength).max().getAsInt();
        int result2 = Arrays.stream(decreaseMaxLength).max().getAsInt();
        System.out.println(Math.max(result2, result));
    }
}

