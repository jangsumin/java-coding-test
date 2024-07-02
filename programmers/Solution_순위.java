import java.io.*;
import java.util.*;

class Solution_순위 {
    public int solution(int n, int[][] results) {
        int[][] matrix = new int[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
                if (i == j) matrix[i][j] = 0;
            }
        }
        
        for (int[] result : results) {
            matrix[result[0]][result[1]] = 1; // 1이면 이김
            matrix[result[1]][result[0]] = -1; // -1이면 짐
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (matrix[i][k] == 1 && matrix[k][j] == 1) {
                        matrix[i][j] = 1;
                    } else if (matrix[i][k] == -1 && matrix[k][j] == -1) {
                        matrix[i][j] = -1;
                    }
                }
            }
        }
        
        // for (int[] row : matrix) System.out.println(Arrays.toString(row));
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean isPossible = true;
            
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE) {
                    isPossible = false;
                    break;
                }
            }
            
            if (isPossible) answer++;
        }
        
        return answer;
    }
}
