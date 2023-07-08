package swExpertAcademy;
// 17299 최소 덧셈(d4)
import java.io.*;

public class Main_17299 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int i = 1; i <= tc; i++) {
			String input = br.readLine();
			int result = 2147483647;
			for (int j = 1; j < input.length(); j++) {
				String front = input.substring(0, j);
				String back = input.substring(j);
				if (Integer.parseInt(front) + Integer.parseInt(back) < result) {
					result = Integer.parseInt(front) + Integer.parseInt(back);
				}
			}
			System.out.println(String.format("%s%d %d", "#", i, result));
		}	
	}
}