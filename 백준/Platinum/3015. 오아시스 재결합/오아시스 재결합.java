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
            while (!stack.isEmpty() && stack.peek()[0] < target[0]) {
                stack.pop();
                result++;
            }
            if (!stack.isEmpty()) {
                if (stack.peek()[0] == target[0]) {
                    result += stack.peek()[1];
                    target[1] = stack.peek()[1] + 1;
                    if (stack.size() > stack.peek()[1]) {
                        result++;
                    }
                }
                else {
                    result++;
                }
            }
            stack.push(target);
        }
        System.out.println(result);
    }
}


