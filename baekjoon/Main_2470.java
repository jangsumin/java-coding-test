package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2470 {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// System.out.println(Arrays.toString(arr));
		
		Arrays.sort(arr); // 정렬
		// -99 -2 -1 4 98
		
		int result = 2000000000;
		int el1 = 0, el2 = 0;
		
		for (int i = 0, j = N - 1; i < j;) {
			int sum = arr[i] + arr[j];
			if (Math.abs(sum) < result) {
				el1 = arr[i];
				el2 = arr[j];
				result = Math.abs(sum);
			}
			if (sum == 0) {
				System.out.println(arr[i] + " " + arr[j]);
				return;
			}
			if (sum < 0) i++;
			if (sum > 0) j--;
		}
		
		System.out.println(el1 + " " + el2);
		
	}

}
