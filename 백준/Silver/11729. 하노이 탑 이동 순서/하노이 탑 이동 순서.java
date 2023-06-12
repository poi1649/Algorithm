import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        System.out.println((int) Math.pow(2, n) - 1);
        StringBuilder sb = new StringBuilder();
        hanoi(n, 1, 2, 3, sb);
        System.out.print(sb.substring(0, sb.length() - 1));
    }

    private static void hanoi(int n, int start, int mid, int end, StringBuilder sb) {
        if (n == 1) {
            sb.append(start).append(" ").append(end).append(System.lineSeparator());
            return;
        }
        hanoi(n - 1, start, end, mid, sb);
        sb.append(start).append(" ").append(end).append(System.lineSeparator());
        hanoi(n - 1, mid, start, end, sb);
    }
}
