import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        Deque<int[]> stack = new LinkedList<>();
        long result = 0;
        for (int i = 0; i < n; i++) {
            final int[] target = new int[2];
            target[0] = Integer.parseInt(br.readLine());
            target[1] = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= target[0]) {
                final int[] pop = stack.pop();
                result += pop[1];
                if (pop[0] == target[0]) target[1] += pop[1];
            }
            if (!stack.isEmpty()) {
                result++;
            }
            stack.push(target);
        }
        System.out.println(result);
    }
}


