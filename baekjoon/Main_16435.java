package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16435 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] fruits = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruits);
		for (int i = 0; i < N; i++) {
			if (fruits[i] <= L) L++;
		}
		
		System.out.println(L);
	}

}
