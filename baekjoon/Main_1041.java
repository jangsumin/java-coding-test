package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1041 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		long N = Integer.parseInt(sc.nextLine());
		
		long[] nums = new long[6];
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for (int i = 0; i < 6; i++) nums[i] = Long.parseLong(st.nextToken());
		
		// 1개 조합
		// A, B, C, D, E, F
		// 붙어있는 2개 조합
		// AB, AC, AD, AE, BC, BD, BF, CE, CF, DE, DF, EF
		// 붙어있는 3개 조합
		// ABC, ABD, BCF, BDF, ACE, ADE, DEF, CEF
		
		long[] combOfTwo = {
				nums[0] + nums[1], nums[0] + nums[2],
				nums[0] + nums[3], nums[0] + nums[4],
				nums[1] + nums[2], nums[1] + nums[3],
				nums[1] + nums[5], nums[2] + nums[4],
				nums[2] + nums[5], nums[3] + nums[4],
				nums[3] + nums[5], nums[4] + nums[5]
		};
		long[] combOfThree = {
				nums[0] + nums[1] + nums[2], nums[0] + nums[1] + nums[3],
				nums[0] + nums[2] + nums[4], nums[0] + nums[3] + nums[4],
				nums[1] + nums[2] + nums[5], nums[1] + nums[3] + nums[5],
				nums[2] + nums[4] + nums[5], nums[3] + nums[4] + nums[5],
		};
		
		Arrays.sort(nums);
		Arrays.sort(combOfTwo);
		Arrays.sort(combOfThree);		
		
		// 결과 long으로 반환해야 함.
		long result = 0;
		
		if (N == 1) {
			System.out.println(nums[0] + nums[1] + nums[2] + nums[3] + nums[4]);
			sc.close();
			return;
		}
		
		// 노출되는 면이 1개, 2개, 3개인 정육면체를 고려하기
		
		// 노출되는 총 면 수는 (n * n) * 5
		// 3개인 면은 4개
		// 2개인 면은 총 면 수에서 1개인 면이랑 3개인 면 빼기
		// 1개인 면은 (n - 2) * (n - 2) * 5 + (n - 2) * 4
		
		long cntExposeOne = ((N - 2) * (N - 2) * 5 + (N - 2) * 4);
		long cntExposeThree = 4;
		long cntExposeTwo = (N * N * 5) - cntExposeOne - cntExposeThree * 3;
		
		result += cntExposeOne * nums[0]; // 1개인 면
		result += cntExposeTwo * combOfTwo[0] / 2; // 2개인 면
		result += cntExposeThree * combOfThree[0]; // 3개인 면
		
		System.out.println(result);
		sc.close();
	}

}
