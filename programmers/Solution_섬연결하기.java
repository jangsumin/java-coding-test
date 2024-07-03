import java.io.*;
import java.util.*;

class Solution_섬연결하기 {
    static int[] uf;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        // for (int[] cost : costs) System.out.println(Arrays.toString(cost));
        
        int minCost = 0;
        
        init(n);
        
        for (int i = 0; i < costs.length; i++) {
            int x = costs[i][0];
            int y = costs[i][1];
            int cost = costs[i][2];
            
            if (find(x) != find(y)) {
                minCost += cost;
                union(x, y);
            }
        }
        
        // System.out.println(Arrays.toString(uf));
        
        return minCost;
    }
    
    // 부모-자식 노드 관계를 표현하기 위한 배열 설정
    void init(int length) {
        uf = new int[length];
        for (int i = 0; i < length; i++) uf[i] = i; // 해당 노드의 부모는 자신
    }
    
    int find(int x) {
        if (x == uf[x]) return x;
        return uf[x] = find(uf[x]);
    }
    
    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        uf[px] = py;
    }
}
