package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1759 {
	static int L, C;
	static char[] arr;
	static char[] save;
	static char[] aeiou = {'a', 'e', 'i', 'o', 'u'}; 
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		save = new char[L];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) arr[i] = st.nextToken().charAt(0);
		
		Arrays.sort(arr);
		// System.out.println(Arrays.toString(arr));
		comb(0, 0);
	}
	
	static void comb(int cnt, int start) {
		if (cnt == L) {
			int isAEIOU = 0;
			for (char el : save) {
				for (int i = 0; i < 5; i++) {
					if (el == aeiou[i]) {
						isAEIOU += 1;
					}
				}
			}
			int isRest = L - isAEIOU;
			
			if (!(isAEIOU >= 1 && isRest >= 2)) return;
			
			String answer = "";
			for (int i = 0; i < L; i++) {
				answer += save[i];
			}
			System.out.println(answer);
			return;
		}
		
		for (int i = start; i < C; i++) {
			save[cnt] = arr[i];
			comb(cnt + 1, i + 1);
		}
	}

}
