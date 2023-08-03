package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int s_length = Integer.parseInt(st.nextToken());
		int p_length = Integer.parseInt(st.nextToken());
		char[] input_str = br.readLine().toCharArray();
		st = new StringTokenizer(br.readLine(), " ");
		// A, C, G, T 순
		int result = 0;
		int[] min_cnt = new int[4];
		for (int i = 0; i < 4; i++) {
			min_cnt[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] check_cnt = new int[4]; 
		for (int i = 0; i < p_length; i++) {
			if (input_str[i] == 'A') check_cnt[0] += 1;
			if (input_str[i] == 'C') check_cnt[1] += 1;
			if (input_str[i] == 'G') check_cnt[2] += 1;
			if (input_str[i] == 'T') check_cnt[3] += 1;
		}
		
		if(checkString(check_cnt, min_cnt)) result++;
		
		int i = 0;
		for (int j = p_length; j < s_length; j++) {
			// sb.append(input_str[i + j]);
			i = j - p_length;
			if (input_str[i] == 'A') check_cnt[0]--;
			if (input_str[i] == 'C') check_cnt[1]--;
			if (input_str[i] == 'G') check_cnt[2]--;
			if (input_str[i] == 'T') check_cnt[3]--;
			
			if (input_str[j] == 'A') check_cnt[0] += 1;
			if (input_str[j] == 'C') check_cnt[1] += 1;
			if (input_str[j] == 'G') check_cnt[2] += 1;
			if (input_str[j] == 'T') check_cnt[3] += 1;
			
			if(checkString(check_cnt, min_cnt)) result++;
		}
		
		System.out.println(result);
	}

	public static boolean checkString(int[] check, int[] condition) {
		for (int j = 0; j < 4; j++) {
			if (check[j] < condition[j]) {
				return false;
			}
		}
		return true;
	}
}
