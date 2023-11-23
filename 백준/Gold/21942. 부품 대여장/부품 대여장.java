import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Main {

    static int t;
    static int f;
    static int n;
    static StringBuilder sb = new StringBuilder();

    static class ProductPerson {
        String name;
        String product;

        public ProductPerson(final String name, final String product) {
            this.name = name;
            this.product = product;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final ProductPerson that = (ProductPerson) o;
            return Objects.equals(name, that.name) && Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, product);
        }
    }

    static class PersonTime {

        String name;
        long time;

        public PersonTime(final String name, final long time) {
            this.name = name;
            this.time = time;
        }
    }

    static Map<ProductPerson, Long> map = new HashMap<>();
    static Map<String, Long> fmap = new TreeMap<>();
    static Map<Integer, Integer> daymap = new HashMap<>(Map.of(
            1, 0,
            2, 31,
            3, 31 + 28,
            4, 31 + 28 + 31,
            5, 31 + 28 + 31 + 30,
            6, 31 + 28 + 31 + 30 + 31
    ));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int p) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        daymap.putAll(
                Map.of(
                        7, 31 + 28 + 31 + 30 + 31 + 30,
                        8, 31 + 28 + 31 + 30 + 31 + 30 + 31,
                        9, 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31,
                        10, 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30,
                        11, 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31,
                        12, 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30
                )
        );

        final var daytime = s[1].split("/");
        final var time = daytime[1].split(":");
        long maxGap = parseLong(daytime[0]) * 1440 + parseLong(time[0]) * 60 + parseInt(time[1]);

        f = parseInt(s[2]);

        for (int i = 0; i < n; i++) {
            final var s1 = br.readLine().split(" ");

            final var times = s1[1].split(":");
            final var hour = times[0];
            final var min = times[1];

            final var days = s1[0].split("-");
            final var mm = parseInt(days[1]);
            final var dd = days[2];

            long day = daymap.get(mm) + parseInt(dd) - 1;
            long t = parseLong(min) + parseLong(hour) * 60 + day * 1440;

            final var product = s1[2];
            final var name = s1[3];

            ProductPerson pp = new ProductPerson(name, product);

            final var v = map.putIfAbsent(pp, t);

            if (v == null) {
                continue;
            }

            final long gap = t - v;
            map.remove(pp);
            if (gap > maxGap) {
                fmap.merge(name, ((gap - maxGap) * f), Long::sum);
            }
        }

        fmap.forEach((s2, integer) -> sb.append(s2).append(" ").append(integer).append("\n"));

        if (sb.length() == 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(sb);
    }
}


