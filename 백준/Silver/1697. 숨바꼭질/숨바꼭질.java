import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int[] maps;
    static int n;
    static int m;
    static Deque<Integer> routes = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        maps = new int[100001];
        routes.addLast(n);

        while (!routes.isEmpty()) {
            final int target = routes.pollFirst();
            if (target == m) {
                System.out.println(maps[m]);
                return;
            }
            check(target, maps[target]);
        }
    }

    static void check(int index, int depth) {
        if (index < 100000 && maps[index + 1] == 0) {
            maps[index + 1] = depth + 1;
            routes.addLast(index + 1);
        }
        if (index > 0 && maps[index - 1] == 0) {
            maps[index - 1] = depth + 1;
            routes.addLast(index - 1);
        }
        if (index <= 50000 && maps[index * 2] == 0) {
            maps[index * 2] = depth + 1;
            routes.addLast(index * 2);
        }
    }
}

