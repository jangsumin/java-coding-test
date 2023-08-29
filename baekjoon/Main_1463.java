package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1463 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] save = new int[N + 1];
		Arrays.fill(save, Integer.MAX_VALUE);
		save[0] = 0;
		save[1] = 0;
		for (int i = 1; i <= N; i++) {
			if (i + 1 <= N) save[i + 1] = Math.min(save[i + 1], save[i] + 1);
			if (i * 2 <= N) save[i * 2] = Math.min(save[i * 2], save[i] + 1);
			if (i * 3 <= N) save[i * 3] = Math.min(save[i * 3], save[i] + 1);
		}
		
		System.out.println(save[N]);
	}

}
