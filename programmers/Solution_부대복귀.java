import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int index;
    int cost;
    
    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

class Solution_부대복귀 {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] dist = new int[n + 1];
        boolean[] v = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) list[i] = new ArrayList<Integer>(); 
        
        for (int[] road : roads) {
            list[road[0]].add(road[1]);
            list[road[1]].add(road[0]);
        }
        
        dist[destination] = 0; // 두 지역은 왕복이 가능하므로 destination에서 거꾸로 접근
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(destination, 0));
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            
            if (v[curr.index]) continue;
            v[curr.index] = true;
            
            for (int next : list[curr.index]) {
                if (dist[next] > curr.cost + 1) {
                    dist[next] = curr.cost + 1;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }
        
        // System.out.println(Arrays.toString(dist));
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] == Integer.MAX_VALUE) answer[i] = -1;
            else answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}
