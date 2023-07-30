import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static int[][] cost;
    static int[] nodes;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        nodes = new int[100001];
        int count = 0;
        while ((input = br.readLine()) != null && !input.equals("")) {
            nodes[count] = Integer.parseInt(input);
            count++;
        }
        check(0, count - 1);
        System.out.println(sb);
    }

    static void check(int start, int end) {

        if (start > end) {
            return;
        }

        if (start == end) {
            sb.append(nodes[start]).append(System.lineSeparator());
            return;
        }

        int root = nodes[start];
        int mid = -1;

        for (int i = start + 1; i <= end; i++) {
            if (nodes[i] > root) {
                mid = i;
                break;
            }
        }
        if (mid == -1) {
            mid = end + 1;
        }

        check(start + 1, mid - 1);
        check(mid, end);
        sb.append(root).append(System.lineSeparator());
    }
}


