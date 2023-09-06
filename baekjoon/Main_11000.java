package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11000 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			list.add(new int[] {s, t});
		}
		
		Collections.sort(list, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
		// for (int[] el : list) System.out.println(Arrays.toString(el));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int end_time = 0;
		for (int[] cls : list) {
			end_time = cls[1];
			
			if (!pq.isEmpty() && pq.peek() <= cls[0]) {
				pq.poll();
			}
			
			pq.add(end_time);
			// System.out.println(pq.toString());
		}
		
		System.out.println(pq.size());
	}

}
