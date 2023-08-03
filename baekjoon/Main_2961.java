package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2961 {
	static int N;
	static int[] Sa, Ba;
	static boolean[] v;
	static int min_diff = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Sa = new int[N];
		Ba = new int[N];
		v = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			Sa[i] = S; Ba[i] = B;
		}
		
//		System.out.println(Arrays.toString(Sa));
//		System.out.println(Arrays.toString(Ba));
		
		subset(0);
		System.out.println(min_diff);
		br.close();
	}
	
	public static void subset(int cnt) {
		if (cnt == N) {
			boolean isNotEmpty = false;
			for (boolean el : v) {
				if (el == true) {
					isNotEmpty = true;
					break;
				}
			}
			
			if (isNotEmpty) {
				int total_S = 1;
				int total_B = 0;
				for (int i = 0; i < N; i++) {
					if (v[i]) {
						total_S *= Sa[i];
						total_B += Ba[i];
					}
				}
				if (Math.abs(total_S - total_B) < min_diff) min_diff = Math.abs(total_S - total_B);
			}
			return;
		}
		
		v[cnt] = true;
		subset(cnt + 1);
		v[cnt] = false;
		subset(cnt + 1);
	}

}
