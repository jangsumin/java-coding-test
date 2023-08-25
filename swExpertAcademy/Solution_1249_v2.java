package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1249_v2 {
  static int N;
  static int[][] board;
  static boolean[][] v;
  static int[][] dist;
  static final int[] di = {-1, 0, 1, 0};
  static final int[] dj = {0, 1, 0, -1};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];
      v = new boolean[N][N];
      dist = new int[N][N];
      for (int i = 0; i < N; i++) {
        String[] input = br.readLine().split("");
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(input[j]);
          dist[i][j] = Integer.MAX_VALUE;
        }
      }
      
      /* board
      [0, 1, 0, 0]
      [1, 1, 1, 0]
      [1, 0, 1, 1]
      [1, 0, 1, 0]
      */
      
      /* dist
      [0, INF, INF, INF]
      [INF, INF, INF, INF]
      [INF, INF, INF, INF]
      [INF, INF, INF, INF]
      
      [0  , 1  , INF, INF]
      [1  , INF, INF, INF]
      [INF, INF, INF, INF]
      [INF, INF, INF, INF]
      
      [0  , 1  , 1  , INF]
      [1  , 2  , INF, INF]
      [INF, INF, INF, INF]
      [INF, INF, INF, INF]
      
      [0  , 1  , 1  , 1  ]
      [1  , 2  , 2  , INF]
      [INF, INF, INF, INF]
      [INF, INF, INF, INF]
      
      [0  , 1  , 1  , 1  ]
      [1  , 2  , 2  , 1  ]
      [INF, INF, INF, INF]
      [INF, INF, INF, INF]
      
      [0  , 1  , 1  , 1  ]
      [1  , 2  , 2  , 1  ]
      [2  , INF, INF, INF]
      [INF, INF, INF, INF]
      
      .
      .
      .
      
      [0  , 1  , 1  , 1  ]
      [1  , 2  , 2  , 1  ]
      [2  , 2  , 3  , 2  ]
      [3  , 2  , 3  , 2  ]
      
      */

      // for (int[] row : board) System.out.println(Arrays.toString(row));
      
      dist[0][0] = 0;
      for (int i = 0; i < N; i++) {
    	  for (int j = 0; j < N; j++) {
    		  int min_i = -1, min_j = -1;
    		  int min = Integer.MAX_VALUE;
    		  
    		  for (int k = 0; k < N; k++) {
    			  for (int l = 0; l < N; l++) {
    				  if (!v[k][l] && min > dist[k][l]) {
    					  min_i = k; min_j = l;
    					  min = dist[k][l];
    				  }
    			  }
    		  }
    		  
    		  v[min_i][min_j] = true;
    		  if (min_i == N - 1 && min_j == N - 1) break;
    		  for (int d = 0; d < 4; d++) { // 4방 탐색
    			  int ni = min_i + di[d];
    			  int nj = min_j + dj[d];
    			  if (ni >= 0 && ni < N && nj >= 0 && nj < N && !v[ni][nj] && dist[ni][nj] > min + board[ni][nj]) {
    				  dist[ni][nj] = min + board[ni][nj];
    			  }
    		  }
    	  }
      }
      
      // for (int[] row : dist) System.out.println(Arrays.toString(row));
      System.out.println(String.format("#%d %d", tc, dist[N - 1][N - 1]));  
    }
    br.close();
  }
}

/*
2 
4
0100
1110
1011
1010
6
011001
010100
010011
101001
010101
111010
*/
