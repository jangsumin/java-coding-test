package baekjoon;

import java.io.*;
import java.util.*;

public class Main_5430 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < T; tc++) {
			String[] cmd = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			String str_arr = br.readLine();
			
			if (n != 0) { // 0이 아닌 경우 처리
				int idx = 0;
				StringBuilder acc = new StringBuilder();
				ArrayDeque<Integer> q = new ArrayDeque<>();
				while (idx < str_arr.length()) {
					if (str_arr.charAt(idx) == '[') {
						idx++;
						continue;
					}
					else if (str_arr.charAt(idx) == ']') {
						q.offer(Integer.parseInt(acc.toString()));
						acc = new StringBuilder();
						break;
					}
					else if (str_arr.charAt(idx) == ',') {
						q.offer(Integer.parseInt(acc.toString()));
						acc = new StringBuilder();
					} else {
						acc.append(str_arr.charAt(idx));
					}
					
					idx++;
				}
				
				// System.out.println(q.toString());
				boolean isError = false;
				boolean reverse = false;
				for (int j = 0; j < cmd.length; j++) {
					if (cmd[j].equals("R")) {
						reverse = !reverse;
					}
					if (cmd[j].equals("D")) {
						if (q.size() == 0) {
							isError = true;
							break;
						}
						else {
							if (reverse) q.removeLast();
							else q.removeFirst();
						}
					}
				}
				
				int q_size = q.size();
				if (isError) sb.append("error").append("\n");
				else {
					sb.append("[");
					if (reverse) {
						for (int j = 0; j < q_size; j++) {
							int q_poll = q.pollLast();
							if (j != q_size - 1) {
								sb.append(q_poll).append(",");
							}
							else sb.append(q_poll);
						}						
					} else {
						for (int j = 0; j < q_size; j++) {
							int q_poll = q.poll();
							if (j != q_size - 1) {
								sb.append(q_poll).append(",");
							}
							else sb.append(q_poll);
						}	
					}
					sb.append("]").append("\n");
				}
				
			} else { // 0인 경우 처리
				boolean isError = false;
				for (int i = 0; i < cmd.length; i++) {
					if (cmd[i].equals("D")) {
						isError = true;
						break;
					}
				}
				if (isError) {
					sb.append("error").append("\n");
				} else {
					sb.append("[]").append("\n");
				}
			}
			
		}
		
		System.out.print(sb.toString());
	}

}
