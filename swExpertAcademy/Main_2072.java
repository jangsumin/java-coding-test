package swExpertAcademy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2072 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d1_2072.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int sum = 0;
			for (int j = 0; j < 10; j++) {
				int el = Integer.parseInt(st.nextToken());
				if (el % 2 == 1) {
					sum += el;
				}
			}
			sb.append("#").append(i).append(" ").append(sum).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}