package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2805 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] trees = new int[N]; 
		st = new StringTokenizer(br.readLine(), " ");
		long min_height = 0;
		long max_height = 0;
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max_height = Math.max(max_height, trees[i]);
		}
		
		long mid = 0;
		while (min_height <= max_height) {
			mid = (max_height + min_height) / 2;
			// System.out.println(mid);
			long sum = 0;
			for (int i = 0; i < N; i++) {
				if (trees[i] > mid) sum += trees[i] - mid;
			}
			
			if (sum >= M) min_height = mid + 1;
			if (sum < M) max_height = mid - 1;
		}
		
		 System.out.println(max_height);
	}

}
