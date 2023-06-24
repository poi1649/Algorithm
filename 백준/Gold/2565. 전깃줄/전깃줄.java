import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            final String[] s = br.readLine().split(" ");
            treeMap.put(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        final int[] arr = treeMap.values().stream().mapToInt(value -> value).toArray();
        int[] maxLength = new int[n];
        Arrays.fill(maxLength, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    maxLength[i] = Math.max(maxLength[j] + 1, maxLength[i]);
                }
            }
        }

        int result = Arrays.stream(maxLength).max().getAsInt();
        System.out.println(n - result);
    }
}

