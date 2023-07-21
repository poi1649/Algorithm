
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    static int m;
    static int n;
    static int[] inputs;
    
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> stack = new LinkedList<>();


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(inputs);
        check(0, 0);
        System.out.print(sb);
    }

    static void check(int index, int depth) {
        if (depth == m) {
            sb.append(stack.stream().map(String::valueOf).collect(Collectors.joining(" ")))
                    .append(System.lineSeparator());
            return;
        }

        for (int i = index; i < n; i++) {
            stack.add(inputs[i]);
            check(i, depth + 1);
            stack.pollLast();
        }
    }
}
