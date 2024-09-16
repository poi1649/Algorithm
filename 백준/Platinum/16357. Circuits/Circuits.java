import java.io.*;
import java.util.*;

public class Main {
    static final int SIZE = 200001;
    static Set<Integer> SET = new HashSet<>();
    static List<Integer> CC = new ArrayList<>();
    static List<int[]> range = new ArrayList<>();
    static List<int[]>[] regY = new List[SIZE];
    static int[] segTree = new int[SIZE * 4];
    static int[] lazy = new int[SIZE * 4];

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] split = reader.readLine().split(" ");
            int ux = Integer.parseInt(split[0]);
            int uy = Integer.parseInt(split[1]);
            int vx = Integer.parseInt(split[2]);
            int vy = Integer.parseInt(split[3]);

            range.add(new int[]{vy, uy, 0});  // Bottom edge
            range.add(new int[]{uy, vy, 1});  // Top edge
            SET.add(uy);
            SET.add(vy);
        }

        range.sort(Comparator.comparingInt(a -> a[0]));
        CC.addAll(SET);
        Collections.sort(CC);

        for (int[] r : range) {
            int y1 = Collections.binarySearch(CC, r[0]) + 1;
            int y2 = Collections.binarySearch(CC, r[1]) + 1;
            if (regY[y1] == null) regY[y1] = new ArrayList<>();
            regY[y1].add(new int[]{y2, r[2]});
            if (r[2] == 0) update(1, SIZE, y1, y2, 1, 1);
        }

        int cross = 0;
        int ans = 0;
        for (int y1 = 1; y1 < SIZE; y1++) {
            if (regY[y1] != null) {
                for (int[] r : regY[y1]) {
                    if (r[1] == 0) {
                        update(1, SIZE, y1, r[0], 1, -1);
                        cross++;
                    }
                }
            }
            ans = Math.max(ans, cross + segTree[1]);

            if (regY[y1] != null) {
                for (int[] r : regY[y1]) {
                    if (r[1] == 1) cross--;
                }
            }
        }
        System.out.println(ans);
    }

    static void setLazy(int s, int e, int idx) {
        int val = lazy[idx];
        lazy[idx] = 0;

        segTree[idx] += val;
        if (s != e) {
            lazy[idx * 2] += val;
            lazy[idx * 2 + 1] += val;
        }
    }

    static void update(int s, int e, int l, int r, int idx, int val) {
        if (lazy[idx] != 0) setLazy(s, e, idx);

        if (e < l || r < s) return;
        if (l <= s && e <= r) {
            segTree[idx] += val;
            if (s != e) {
                lazy[idx * 2] += val;
                lazy[idx * 2 + 1] += val;
            }
            return;
        }

        update(s, (s + e) / 2, l, r, idx * 2, val);
        update((s + e) / 2 + 1, e, l, r, idx * 2 + 1, val);
        segTree[idx] = Math.max(segTree[idx * 2], segTree[idx * 2 + 1]);
    }
}