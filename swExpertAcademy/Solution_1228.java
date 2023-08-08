package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1228 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = 10;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> proto = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) proto.add(Integer.parseInt(st.nextToken()));
			
			int commandN = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			while (st.countTokens() != 0) {
				if (st.nextToken().equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						proto.add(x, Integer.parseInt(st.nextToken()));
						x++;
					}
				}
			}
			
			sb.append("#").append(tc);
			for (int i = 0; i < 10; i++) {
				sb.append(" ").append(proto.get(i));
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
