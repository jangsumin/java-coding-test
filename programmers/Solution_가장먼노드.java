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

class Solution_가장먼노드 {
    public int solution(int n, int[][] edge) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        boolean[] v = new boolean[n + 1];
        
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) list[i] = new ArrayList<>();
        
        for (int[] e : edge) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        
        dist[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        
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
        
        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }
        
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (maxDist == dist[i]) result++;
        }
        
        return result;
    }
}
