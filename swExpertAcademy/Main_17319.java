package swExpertAcademy;
import java.io.*;

public class Main_17319 {
  public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int i = 1; i <= tc; i++) {
			int n = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split("");
			
			String result = "Yes";
			if (n % 2 != 0) {
				result = "No";
			}
			String temp1 = "";
			String temp2 = "";
			for (int j = 0; j < n; j++) {
				if (j < n / 2) {
					temp1 += str[j];
				} else {
					temp2 += str[j];
				}
			}
			if (!(temp1.equals(temp2))) {
				result = "No";
			}
			System.out.println(String.format("%s%d %s", "#", i, result));
		}
	}
}