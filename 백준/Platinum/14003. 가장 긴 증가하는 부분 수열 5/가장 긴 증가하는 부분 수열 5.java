import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final long[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        final long[] result = new long[n];
        final long[] memo = new long[n];

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
            memo[i] = targetIndex;
        }
        System.out.println(index);
        final LinkedList<Long> last = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (index == 0) {
                break;
            }
            if (memo[i] == index - 1) {
                last.addFirst(arr[i]);
                index--;
            }
        }
        System.out.println(last.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}

