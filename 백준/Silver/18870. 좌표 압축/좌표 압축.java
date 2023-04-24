import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        final List<Integer> ints = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        final List<Integer> copy = new ArrayList<>(ints);
        Collections.sort(ints);
        AtomicInteger i = new AtomicInteger(0);
        Map<Integer, Integer> result = new HashMap<>();
        int previous = ints.get(0);
        result.put(previous, i.get());
        for (int j = 1; j < ints.size(); j++) {
            int current = ints.get(j);
            if (previous == current) {
                continue;
            }
            result.put(current ,i.addAndGet(1));
            previous = current;
        }
        System.out.println(copy.stream()
                .map(result::get)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
