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

class Solution_배달 {
    public int solution(int N, int[][] road, int K) {
        int[] dist = new int[N + 1];
        boolean[] v = new boolean[N + 1];
        for (int i = 1; i <= N; i++) dist[i] = Integer.MAX_VALUE;
        
        // 인접 행렬로 만들기
        int[][] matrix = new int[N + 1][N + 1];
        for (int[] info : road) {
            int start = info[0];
            int end = info[1];
            int distance = info[2];
            
            if (matrix[start][end] != 0 && matrix[start][end] > distance) {
                matrix[start][end] = distance;
                matrix[end][start] = distance;
            }
            
            if (matrix[start][end] == 0) {
                matrix[start][end] = distance;
                matrix[end][start] = distance;   
            }
        }
        
        // for (int[] row : matrix) System.out.println(Arrays.toString(row));
        
        dist[1] = 0; // 1번 마을에서 시작
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int index = node.index;
            int cost = node.cost;
            
            if (v[index]) continue;
            v[index] = true;
            
            for (int i = 1; i < matrix[index].length; i++) {
                if (matrix[index][i] != 0 && dist[i] > cost + matrix[index][i]) {
                    dist[i] = cost + matrix[index][i];
                    pq.offer(new Node(i, dist[i]));
                }  
            }
        }
        
        // System.out.println(Arrays.toString(dist));
        
        int answer = 0;
        
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }
}
