package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int idx = 1; idx <= N; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
		
		int sum = arr[0];
		for (int idx = 1; idx <= N; idx++) {
			arr[idx] += sum;
			sum = arr[idx];
		}
		
		// System.out.println(Arrays.toString(arr));
		StringBuilder sb = new StringBuilder();
		
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken());
			
			sb.append(arr[j] - arr[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

}
