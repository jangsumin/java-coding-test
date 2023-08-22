package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13023 {
	static int N, M;
//	static int[][] matrix;
	static boolean[] v;
	static List<Integer>[] adjList;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 인접 행렬로 풀면 시간 초과
		// matrix = new int[N][N];
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
//			matrix[one][two] = matrix[two][one] = 1;
			adjList[one].add(two);
			adjList[two].add(one);
		}
		
		// for (int[] row : matrix) System.out.println(Arrays.toString(row));
		// for (LinkedList list : adjList) System.out.println(list);
		
		for (int i = 0; i < N; i++) {
			v = new boolean[N];
			dfs(i, 0);
			if (answer == 1) {
				System.out.println(answer);
				return;
			}
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int node, int cnt) {
		if (cnt == 4) {
			answer = 1;
			return;
		}
		v[node] = true;
		for (int nxt : adjList[node]) {
			if (!v[nxt]) dfs(nxt, cnt + 1);
		}
		v[node] = false;
	}

}
