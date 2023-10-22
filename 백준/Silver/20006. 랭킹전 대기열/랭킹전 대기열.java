import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Main {
    static class Node {
        int key;
        String name;

        public Node(int key, String value) {
            this.key = key;
            this.name = value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var s = br.readLine().split(" ");
        final var n = Integer.parseInt(s[0]);
        final var m = Integer.parseInt(s[1]);

        final var map = new LinkedHashMap<Node, List<Node>>();

        for (int i = 0; i < n; i++) {
            final var s1 = br.readLine().split(" ");
            final var key = Integer.parseInt(s1[0]);
            final var value = s1[1];
            final var e = new Node(key, value);

            boolean isExist = false;

            for (var curKey : map.keySet()) {
                if (curKey.key - 10 <= key && key <= curKey.key + 10 && map.get(curKey).size() < m) {
                    map.get(curKey).add(e);
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                map.put(e, new ArrayList<>());
                map.get(e).add(e);
            }
        }

        for (List<Node> nodes : map.values()) {
            if (nodes.size() == m) {
                System.out.println("Started!");
            }
            else {
                System.out.println("Waiting!");
            }
            nodes.sort(Comparator.comparing(o -> o.name));
            for (Node node : nodes) {
                System.out.println(node.key + " " + node.name);
            }
        }
    }
}
