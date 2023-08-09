package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_10580 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			List<int[]> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new int[] {a, b});
			}
			
			Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);
			// for (int[] el : list) System.out.println(Arrays.toString(el));
			
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = list.get(i)[1];
			}
			
			int result = 0;
			while (true) {
				int change = 0;
				for (int i = 0; i < N - 1; i++) {
					if (arr[i] > arr[i + 1]) {
						int temp = arr[i];
						arr[i] = arr[i + 1];
						arr[i + 1] = temp;
						change++;
						result++;
					}
				}
				if (change == 0) break;
			}
			
			System.out.println(String.format("#%d %d", tc, result));
		}
		
	}

}
