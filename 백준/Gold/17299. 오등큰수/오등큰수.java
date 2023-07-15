import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[][] inputAndIndex = new int[n][2];
        Map<Integer, Integer> frequencies = new HashMap<>();
        final String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            inputAndIndex[i][0] = Integer.parseInt(s[i]);
            inputAndIndex[i][1] = i;
            if (frequencies.containsKey(inputAndIndex[i][0])) {
                frequencies.merge(inputAndIndex[i][0], 1, Integer::sum);
                continue;
            }
            frequencies.put(inputAndIndex[i][0], 1);
        }
        final int[] result = new int[n];

        Deque<int[]> stack = new LinkedList<>();
        stack.push(inputAndIndex[0]);
        final StringBuilder sb = new StringBuilder();

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() &&
                    (frequencies.get(stack.peek()[0]) < frequencies.get(inputAndIndex[i][0]))) {
                result[stack.pop()[1]] = inputAndIndex[i][0];
            }
            stack.push(inputAndIndex[i]);
        }

        while(!stack.isEmpty()) {
            result[stack.pop()[1]] = -1;
        }

        for (int i : result) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}


