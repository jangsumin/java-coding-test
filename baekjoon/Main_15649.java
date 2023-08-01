// N과 M(1)
package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649 {
	private static int N;
	private static int M;
	private static int[] arr;
	private static int[] save;
	private static boolean[] selected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		save = new int[M];
		selected = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		
		permutation(0);
	}
	
	private static void permutation(int cnt) {
		if (cnt == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				if (i != M - 1) sb.append(save[i]).append(" ");
				else sb.append(save[i]);
			}
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			save[cnt] = arr[i];
			permutation(cnt + 1);
			selected[i] = false;
		}
	}
}
