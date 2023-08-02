package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2798 {
	static int N, M, R = 3;
	// 카드에 쓰여진 수는 1 ~ 100000이므로 가장 큰 차이는 99999
	static int save_diff = 99999; 
	static int result;
	static int[] a, b;
	
	static void comb(int cnt, int start) {
		if (cnt == R) {
			// System.out.println(Arrays.toString(b));
			int sum = 0;
			for (int el : b) sum += el;
			if (sum <= M && M - sum < save_diff) {
				save_diff = M - sum;
				result = sum;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			b[cnt] = a[i];
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		a = new int[N];
		b = new int[R];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		result = a[0] + a[1] + a[2];
		
		comb(0, 0);
		System.out.println(result);
	}

}
