package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2458 {

//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		
//		boolean[][] v = new boolean[N][N]; // 인접행렬 만들기
//		
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			int a = Integer.parseInt(st.nextToken()) - 1;
//			int b = Integer.parseInt(st.nextToken()) - 1;
//			
//			v[a][b] = true;
//		}
//		
//		
//		for (int k = 0; k < N; k++) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if (v[i][k] && v[k][j]) {
//						v[i][j] = true;
//					}
//				}
//			}
//		}
//		
//		// for (boolean[] row : v) System.out.println(Arrays.toString(row));
//		
//		int answer = 0;
//		boolean check;
//		for (int i = 0; i < N; i++) {
//			check = false;
//			for (int j = 0; j < N; j++) {
//				if (i != j && !v[i][j] && !v[j][i]) {
//					check = true;
//					break;
//				}
//			}
//			
//			if (!check) answer++;
//		}
//		
//		System.out.println(answer);
//	}
	
	
	    private static int n,m;
	    private static boolean[][] graph;
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());
	        graph = new boolean[n+1][n+1];
	        for(int i = 0; i < m; i++){
	            st = new StringTokenizer(br.readLine());
	            int a = Integer.parseInt(st.nextToken());
	            int b = Integer.parseInt(st.nextToken());
	            graph[a][b] = true;
	        }
	        floid();
	        // for (boolean[] row : graph) System.out.println(Arrays.toString(row));
	        int ans = 0;
	        for (int i = 1; i <= n; i++) {
	            int cnt = 0;
	            for(int j = 1; j <= n; j++){
	                if(graph[i][j] || graph[j][i]) cnt ++;
	            }
	            if(cnt == n-1) ans ++;
	        }
	        System.out.println(ans);
	    }
	    private static void floid(){
	        for (int k = 1; k <= n; k++) {
	            for (int i = 1; i <= n; i++) {
	                for(int j = 1; j <= n; j++){
	                    if(i == k || j == k) continue;
	                    if(graph[i][k] && graph[k][j]) graph[i][j] = true;
	                }
	            }
	        }
	    }
}
