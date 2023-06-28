import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final Map<Integer, List<Integer>> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            final String[] s = br.readLine().split(" ");
            final int i1 = Integer.parseInt(s[0]);
            final int i2 = Integer.parseInt(s[1]);
            if (map.containsKey(i2)) {
                map.get(i2).add(i1);
                continue;
            }
            map.put(i2, new ArrayList<>(List.of(i1)));
        }
        final AtomicInteger atomicCount = new AtomicInteger(0);
        final AtomicInteger previous = new AtomicInteger(0);
        map.forEach((end, startList) -> {
            startList.sort((o1, o2) -> o2 - o1);
            boolean set = false;
            for (int start : startList) {
                if (start >= previous.get()) {
                    if (start == end) {
                        atomicCount.getAndIncrement();
                        set = true;
                        continue;
                    }
                    atomicCount.getAndIncrement();
                    set = true;
                    break;
                }
            }
            if (set) {
               previous.set(end);
            }
        });
        System.out.println(atomicCount.get());
    }
}
