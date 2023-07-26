import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] indexes = new int[n];

        final int[] result = new int[n];
        result[0] = ints[0];
        indexes[0] = 0;
        int index = 1;

        for (int i = 0; i < n; i++) {
            int insertion = Arrays.binarySearch(result, 0, index, ints[i]);
            if (insertion < 0) {
                insertion = Math.abs(insertion) - 1;
                if (index == insertion) {
                    index++;
                }
                result[insertion] = ints[i];
            }
            indexes[i] = insertion;
        }

        System.out.println(index);
        int pointer = n - 1;
        int target = index - 1;
        final StringBuilder sb = new StringBuilder();
        while(pointer >= 0 && target >= 0) {
            int targetIndex = indexes[pointer];
            if (targetIndex == target) {
                sb.insert(0, ints[pointer] + " ");
                target--;
            }
            pointer--;
        }
        System.out.println(sb);
    }
}
