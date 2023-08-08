package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_9229 {
	static int N, M;
	static int max_sum = -1;
	static int[] snacks;
	static int[] save = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			max_sum = -1;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snacks = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) snacks[i] = Integer.parseInt(st.nextToken());
			
			comb(0, 0);
			System.out.printf("#%d %d%n", tc, max_sum);
		}
		
		
	}
	
	static void comb(int cnt, int start) {
		if (cnt == 2) {
			// System.out.println(Arrays.toString(save));
			int sum = save[0] + save[1];
			if (sum > max_sum && sum <= M) max_sum = sum;
			return;
		}
		
		for (int i = start; i < N; i++) {
			save[cnt] = snacks[i];
			comb(cnt + 1, i + 1);
		}
	}

}
