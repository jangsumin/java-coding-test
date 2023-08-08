package programmers;

import java.util.*;

class Solution_캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        ArrayDeque<String> q = new ArrayDeque<>();
        for (String city : cities) {
            String nCity = city.toLowerCase();
            if (q.size() != cacheSize) {
                if (q.contains(nCity)) {
                    answer += 1;
                    q.remove(nCity);
                    q.offer(nCity);
                } else {
                    answer += 5;
                    q.offer(nCity);
                }
            } else {
                if (q.contains(nCity)) {
                    answer += 1;
                    q.remove(nCity);
                    q.offer(nCity);
                } else {
                    answer += 5;
                    q.offer(nCity);
                    q.poll();
                }
            }
            // System.out.println(q.toString());
        }
        
        return answer;
    }
}
