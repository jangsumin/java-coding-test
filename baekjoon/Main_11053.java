package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11053 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

		int[] save = new int[N];
		save[0] = 1;
		for (int i = 1; i < N; i++) {
			save[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && save[i] <= save[j]) {
					save[i] = save[j] + 1;
				}
			}
		}
		
		int max = 0;
		for (int el : save) max = Math.max(max, el);
		System.out.println(max);
	}

}
