package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11286 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
			int abs1 = Math.abs(o1);
			int abs2 = Math.abs(o2);
			
			if (abs1 == abs2) return o1 < o2 ? -1 : 1;
			return abs1 - abs2;
		});
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd != 0) q.offer(cmd);
			else {
				if (q.isEmpty()) sb.append(0).append("\n");
				else {
					int el = q.poll();
					sb.append(el).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
