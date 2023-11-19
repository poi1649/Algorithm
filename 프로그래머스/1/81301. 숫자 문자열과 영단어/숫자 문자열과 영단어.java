import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] arr = s.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        for (char c : arr) {
            if (Character.isDigit(c)) {
                sb.append(c);
                continue;
            }
            tmp.append(c);
            if (map.containsKey(tmp.toString())) {
                sb.append(map.get(tmp.toString()));
                tmp = new StringBuilder();
            }
        }
        answer = Integer.parseInt(sb.toString());
        return answer;
    }
}