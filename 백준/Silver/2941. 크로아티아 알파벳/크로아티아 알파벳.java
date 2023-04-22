import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dzLength = 3;
        int length = 2;
        int count = 0;
        String input = br.readLine();
        String replaced = input.replace("dz=", "   ");
        int diff = input.replace(" ", "").length() - replaced.replace(" ", "").length();
        count += diff / dzLength;

        final CroAlpha[] values = CroAlpha.values();
        for (CroAlpha value : values) {
            String replaced2 = replaced.replace(value.value, "  ");
            if (replaced2.equals(replaced)) {
                continue;
            }
            int diff2 = replaced.replace(" ", "").length() - replaced2.replace(" ", "").length();
            count += diff2 / length;
            replaced = replaced2;
        }
        System.out.println(count + replaced.replace(" ", "").length());
    }

    enum CroAlpha {
        CZERO("c="),
        CMI("c-"),
        DMI("d-"),
        LJ("lj"),
        NJ("nj"),
        SN("s="),
        ZN("z="),
        ;

        private final String value;

        CroAlpha(final String value) {
            this.value = value;
        }
    }


}
