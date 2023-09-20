import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, Integer> keyMap = new HashMap<>();


    static class NameAndTime {

        String name;
        int time;

        public NameAndTime(String name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    static List<NameAndTime> inputs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken().replaceAll(":", ""));
        int e = Integer.parseInt(st.nextToken().replaceAll(":", ""));
        int q = Integer.parseInt(st.nextToken().replaceAll(":", ""));
        String temp;
        while ((temp = br.readLine()) != null) {
            st = new StringTokenizer(temp);
            final int i = Integer.parseInt(st.nextToken().replace(":", ""));
            String name = st.nextToken();
            keyMap.putIfAbsent(name, keyMap.size() + 1);
            inputs.add(new NameAndTime(name, i));
        }
        boolean[] checked = new boolean[keyMap.size() + 2];
        inputs.sort(Comparator.comparingInt(o -> o.time));

        int tmp = 0;
        for (int i = 0; i < inputs.size(); i++) {
            final int time = inputs.get(i).time;
            if (time <= s) {
                final String name = inputs.get(i).name;
                checked[keyMap.get(name)] = true;
                continue;
            }
            tmp = i;
            break;
        }
        int count = 0;
        for (int i = tmp; i < inputs.size(); i++) {
            final int time = inputs.get(i).time;
            if (time >= e && time <= q) {
                final String name = inputs.get(i).name;
                if (checked[keyMap.get(name)]) {
                    count++;
                    checked[keyMap.get(name)] = false;
                }
            }
        }
        System.out.println(count);
    }
}
