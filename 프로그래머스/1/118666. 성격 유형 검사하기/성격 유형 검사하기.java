import java.util.*;

class Solution {
    
    Map<Character, Integer> map = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        for (int i = 0; i < survey.length ; i++) {
            int score = choices[i] - 4;
            if (score > 0) {
                map.merge(survey[i].toCharArray()[1], score, Integer::sum);
                continue;
            }
            map.merge(survey[i].toCharArray()[0], -score, Integer::sum);
        }
        StringBuilder sb = new StringBuilder();
        if (map.getOrDefault('T', 0) > map.getOrDefault('R', 0)) {
            sb.append("T");
        }
        else {
            sb.append("R");
        }
        
        if (map.getOrDefault('F', 0)> map.getOrDefault('C', 0)) {
            sb.append("F");
        }
        else {
            sb.append("C");
        }
        
        if (map.getOrDefault('M', 0)> map.getOrDefault('J', 0)) {
            sb.append("M");
        }
        else {
            sb.append("J");
        }
        if (map.getOrDefault('N', 0)> map.getOrDefault('A', 0)) {
            sb.append("N");
        }
        else {
            sb.append("A");
        }
        return sb.toString();
    }
}