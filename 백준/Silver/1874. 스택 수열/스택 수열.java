import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        Deque<Integer> inputs = new ArrayDeque<>();
        Stack<Integer> integerStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            inputs.add(Integer.parseInt(br.readLine()));
        }
        int candidate = 1;
        int poped = 0;
        while (poped < n) {
            final int current = inputs.pop();
            while (candidate <= current) {
                integerStack.push(candidate);
                sb.append("+\n");
                candidate++;
            }
            if (integerStack.pop() == current) {
                sb.append("-\n");
                poped++;
                continue;
            }
            System.out.println("NO");
            return;
        }
        System.out.println(sb);
    }
}
