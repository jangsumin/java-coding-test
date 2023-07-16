package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10811 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] result = new int[N];
		for (int k = 0; k < N; k++) {
			result[k] = k + 1;
		}
		
		for (int k = 0; k < M; k++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st1.nextToken()) - 1;
			int j = Integer.parseInt(st1.nextToken()) - 1;
			
			int range = j - i;
			if (range % 2 == 1) {
				for (int l = 0; l <= range / 2; l++) {
					int temp = result[i + l];
					result[i + l] = result[j - l];
					result[j - l] = temp;
				}
			} else {
				for (int l = 0; l < range / 2; l++) {
					int temp = result[i + l];
					result[i + l] = result[j - l];
					result[j - l] = temp;
				}
			}
		}
		
		for (int k = 0; k < N; k++) {
			sb.append(result[k]);
			if (k != N - 1) {
				sb.append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}