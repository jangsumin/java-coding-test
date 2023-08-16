package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1182 {
	static int N, S;
	static int result = 0;
	static int[] nums;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(sc.nextLine(), " ");
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		isPromising(0, 0);
		System.out.println(result);
	}
	
	static void isPromising(int sum, int idx) {
		if (idx == N) return;

		sum += nums[idx];
		if (sum == S) {
			result++;
		}
		
		isPromising(sum - nums[idx], idx + 1);
		isPromising(sum, idx + 1);
	}

}
