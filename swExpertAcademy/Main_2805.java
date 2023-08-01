package swExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2805 {
	private static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			int start = N / 2;
			int end = N / 2 + 1;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = start; j < end; j++) {
					sum += Character.getNumericValue(map[i][j]);
				}
				if (i < N / 2) {
					start -= 1;
					end += 1;
				} else {
					start += 1;
					end -= 1;
				}
			}
			System.out.println(String.format("#%d %d", tc, sum));
		}

	}

}
