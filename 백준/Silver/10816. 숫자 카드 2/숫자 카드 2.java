import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        final int m = Integer.parseInt(br.readLine());
        final int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] b = Arrays.copyOf(c, c.length);

        final HashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i : b) {
            map.put(i, 0);
        }

        Arrays.sort(b);

        for (int i : a) {
            if (Arrays.binarySearch(b, i) >= 0) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (int i : c) {
            sb.append(map.get(i)).append(" ");
        }
        System.out.print(sb);
    }
}

