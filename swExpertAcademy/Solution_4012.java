// 요리사

package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_4012 {
	static int N;
	static int[] arr, select;
	static int[][] sy;
	static int min_diff = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 서로 다른 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			min_diff = Integer.MAX_VALUE;
			
			// 조합을 고르는 인덱스 배열
			select = new int[N / 2];
			arr = new int[N];
			for (int i = 0; i < N; i++) arr[i] = i;
			
			// 시너지 입력
			sy = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					sy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// for (int[] row : sy) System.out.println(Arrays.toString(row));
			
			// 조합
			comb(0, 0);
			
			sb.append("#").append(tc).append(" ").append(min_diff).append("\n");
		}
		
		System.out.println(sb.toString());

	}
	
	public static void comb(int cnt, int start) {
		if (cnt == N / 2) { // 고른게 N의 절반이 되면 끝
			int[] not_select = new int[N / 2]; // 안 고른 것들끼리 음식 조합이 됨
			int idx = 0;
			for (int i = 0; i < N; i++) {
				boolean isSelected = false;
				for (int el : select) {
					if (el == i) isSelected = true;
				}
				if (!isSelected) not_select[idx++] = i;
			}
			
			//System.out.println(Arrays.toString(select));
			//System.out.println(Arrays.toString(not_select));
			
			int flavor1 = 0;
			int flavor2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					if (contains(select, i) && contains(select, j)) flavor1 += sy[i][j];
					if (contains(not_select, i) && contains(not_select, j)) flavor2 += sy[i][j];
				}
			}
			
			//System.out.println(flavor1);
			//System.out.println(flavor2);
			//System.out.println();
			
			if (Math.abs(flavor1 - flavor2) < min_diff) min_diff = Math.abs(flavor1 - flavor2);
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			select[cnt] = arr[i];
			comb(cnt + 1, i + 1);
		}
	}
	
	public static boolean contains(final int[] arr, final int key) {
        boolean found = false;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]==key) {
                found=true;
            }
        }
        return found; 
    }
}
