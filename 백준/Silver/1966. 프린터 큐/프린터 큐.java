import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        final int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            final int n = in.nextInt();
            int targetIndex = in.nextInt();
            final LinkedList<Integer> lists = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                lists.add(in.nextInt());
            }
            int count = 0;
            while (true) {
                boolean isTarget = false;
                if (0 == targetIndex) {
                    isTarget = true;
                }
                final int current = lists.poll();
                if (lists.stream().anyMatch(e -> e > current)) {
                    lists.addLast(current);
                    if (isTarget) {
                        targetIndex = lists.size() - 1;
                    }
                    else {
                        targetIndex--;
                    }
                    continue;
                }
                if (isTarget) {
                    System.out.println(count + 1);
                    break;
                }
                count++;
                targetIndex--;
            }
        }
    }

}
