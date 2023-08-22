// 계단 오르기

package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2579 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[301];
		for (int i = 1; i <= N; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}
		
//		System.out.println(Arrays.toString(scores));
		
		int[] save = new int[301];
		save[1] = scores[1]; // 10
		save[2] = save[1] + scores[2]; // 30
		
		for (int i = 3; i <= N; i++) {
			save[i] = Math.max(scores[i - 1] + save[i - 3] + scores[i], save[i - 2] + scores[i]);
			// System.out.println(i + "번 째 : " + save[i]);
		}
		
		System.out.println(save[N]);
	}

}
