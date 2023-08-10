package baekjoon;

import java.io.*;
import java.util.*;

public class Main_3040 {
	static int[] kids = new int[9];
	static int[] save = new int[7];
	static int[] result = new int[7];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			kids[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			sb.append(result[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void comb(int cnt, int start) {
		if (cnt == 7) {
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += save[i];
			}
			if (sum == 100) result = save.clone();
			return;
		}
		
		for (int i = start; i < 9; i++) {
			save[cnt] = kids[i];
			comb(cnt + 1, i + 1);
		}
	}

}
