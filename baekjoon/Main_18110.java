package baekjoon;

import java.io.*;
import java.util.*;

public class Main_18110 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 정렬
		Arrays.sort(arr);
		
		int sum = 0;
		int except = (int) Math.round(n * 0.15);
		
		for (int i = except; i < n - except; i++) {
			sum += arr[i];
		}
		
		// System.out.println(sum);
		
		System.out.println(Math.round((1.0) * sum / (n - except * 2)));
	}

}
