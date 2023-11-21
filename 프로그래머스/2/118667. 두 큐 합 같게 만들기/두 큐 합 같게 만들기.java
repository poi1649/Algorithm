import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long gap = 0;
        Deque<Integer> qOne = new LinkedList<>();
        Deque<Integer> qTwo = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            gap += queue1[i];
            gap -= queue2[i];
            qOne.addLast(queue1[i]);
            qTwo.addLast(queue2[i]);
        }
        
        int count = 0;
        
        while (gap != 0 && count <= queue1.length * 4) {
            if (gap > 0) {
                int tmp = qOne.pollFirst();
                gap -= tmp * 2;
                qTwo.addLast(tmp);
            }
            else {
                int tmp = qTwo.pollFirst();
                gap += tmp * 2;
                qOne.addLast(tmp);
            }
            count++;
        }
        if (gap != 0) {
            return -1;
        }
        
        return count;
    }
}