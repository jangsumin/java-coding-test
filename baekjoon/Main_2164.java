package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2164 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			stack.push(N - i + 1);
		}
		
		int result = 0;
		while (!stack.isEmpty()) {
			int last = stack.pop();
			if (stack.isEmpty()) {
				result = last;
				break;
			}
			stack.addLast(stack.pop());
			// System.out.println(stack.toString());
		}
		System.out.println(result);
	}

}
