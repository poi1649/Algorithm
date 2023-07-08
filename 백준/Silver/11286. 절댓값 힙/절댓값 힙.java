import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueueLowest = new PriorityQueue<>((o1, o2) -> {
            final int abs1 = Math.abs(o1);
            final int abs2 = Math.abs(o2);
            if (abs1 == abs2) {
                if (o1 > o2) {
                    return 1;
                }
                return -1;
            }
                return abs1 - abs2;
        });
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final int target = Integer.parseInt(br.readLine());
            if (target == 0) {
                if (priorityQueueLowest.isEmpty()) {
                    sb.append(0).append(System.lineSeparator());
                    continue;
                }
                sb.append(priorityQueueLowest.poll()).append(System.lineSeparator());
                continue;
            }
            priorityQueueLowest.add(target);
        }
        System.out.print(sb);
    }
}


