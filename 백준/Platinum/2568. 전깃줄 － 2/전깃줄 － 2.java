import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] result;
    static int[] indexes;
    static int longest = 0;
    static int longestIndex = 1;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        final TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            final String[] s = br.readLine().split(" ");
            map.put(
                    Integer.parseInt(s[0]),
                    Integer.parseInt(s[1])
            );
        }
        result = new int[n];
        indexes = new int[n];
        visited = new boolean[n];

        final int[] ints = map.values().stream().mapToInt(Integer::intValue).toArray();

        int size = 1;
        result[0] = ints[0];
        indexes[0] = 0;

        for (int i = 1; i < ints.length; i++) {
            int target = ints[i];
            final int insertion = Math.abs(Arrays.binarySearch(result, 0, size, target)) - 1;
            if (insertion >= size) {
                size++;
            }
            result[insertion] = target;
            indexes[i] = insertion;
        }

        int targetIndex = size - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (indexes[i] == targetIndex) {
                visited[i] = true;
                targetIndex--;
            }
            if (targetIndex < 0) {
                break;
            }
        }

        final int[] keys = map.keySet().stream().mapToInt(Integer::intValue).toArray();

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            sb.append(keys[i]).append(System.lineSeparator());
        }

        System.out.println(n - size);
        System.out.print(sb);
    }
}


