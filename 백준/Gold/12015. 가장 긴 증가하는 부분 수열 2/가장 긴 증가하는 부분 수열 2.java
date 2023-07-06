import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        final int[] result = new int[n];

        int index = 0;

        for (int i = 0; i < n; i++) {
            int targetIndex = Arrays.binarySearch(result, 0, index, arr[i]);
            if (targetIndex < 0) {
                targetIndex = Math.abs(targetIndex + 1);
                if (targetIndex >= index) {
                    index++;
                }
                result[targetIndex] = arr[i];
            }
        }
        System.out.println(index);
    }
}

