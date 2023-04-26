import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int i = Integer.parseInt(br.readLine());
        Map<Integer, List<String>> map = new TreeMap<>();
        for (int j = 0; j < i; j++) {
            final String[] input = br.readLine().split(" ");
            final int age = Integer.parseInt(input[0]);
            final String name = input[1];
            final List<String> list = map.computeIfAbsent(age, ignore -> new ArrayList<>());
            list.add(name);
        }
        map.forEach((age, names) -> {
            for (String name : names) {
                System.out.println(age + " " + name);
            }
        });

    }
}
