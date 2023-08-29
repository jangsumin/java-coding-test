package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11727 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 가로 길이를 지정해서 몇 개 저장할 수 있는지 저장하기
		int[] save = new int[1001];
		save[1] = 1;
		save[2] = 3;
		
		for (int i = 3; i <= n; i++) {
			save[i] = (save[i - 1] + save[i - 2] * 2) % 10007;
		}
		
		// System.out.println(Arrays.toString(save));
		
		System.out.println(save[n]);
		sc.close();
	}

}
