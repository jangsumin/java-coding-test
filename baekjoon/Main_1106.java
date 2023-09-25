import java.io.*;
import java.util.*;

public class Main_1106 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[C + 101];
		Arrays.fill(dp, 100000);
		dp[0] = 0;
			
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cost = Integer.parseInt(st.nextToken());
			int person = Integer.parseInt(st.nextToken());
			
			for (int j = person; j <= C + 100; j++) {
				dp[j] = Math.min(dp[j], dp[j - person] + cost);
			}
		}
		
		int min_result = Integer.MAX_VALUE;
		for (int i = C; i <= C + 100; i++) {
			min_result = Math.min(min_result, dp[i]);
		}
		
		// System.out.println(Arrays.toString(dp));
		System.out.println(min_result);
	}
}
