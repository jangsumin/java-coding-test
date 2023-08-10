package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_6808 {
	static int[] kyu = new int[9];
	static int[] in = new int[9];
	static int[] a = new int[9];
	static boolean[] v = new boolean[9];
	static int win = 0, lose = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			boolean[] check = new boolean[18]; // 인영이가 받아야 할 카드를 체크하는 배열
			for (int i = 0; i < 9; i++) {
				kyu[i] = Integer.parseInt(st.nextToken());
				check[kyu[i] - 1] = true;
			}
			
			int idx = 0;
			for (int i = 0; i < 18; i++) {
				if (!check[i]) in[idx++] = i + 1;
			}
			
			a = new int[9];
			v = new boolean[9];
			win = 0; lose = 0;
			
			perm(0);
			System.out.println(String.format("#%d %d %d", tc, win, lose));
			
		}
		
	}
	
	static void perm(int cnt) {
		if (cnt == 9) {
			// 규영이와 인영이 점수 계산 및 이기는 경우 계산
			int kyu_score = 0;
			int in_score = 0;
			
			for (int i = 0; i < 9; i++) {
				if (kyu[i] > a[i]) kyu_score += kyu[i] + a[i];
				else in_score += kyu[i] + a[i];
			}
			
			if (kyu_score > in_score) win += 1;
			if (in_score > kyu_score) lose += 1;
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (v[i]) continue;
			v[i] = true;
			a[cnt] = in[i];
			perm(cnt + 1);
			v[i] = false;
		}
	}
}
