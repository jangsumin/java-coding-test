package baekjoon;
import java.io.*;

// 대소문자 바꾸기
public class Main_2744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                System.out.print(String.valueOf(c).toLowerCase());
            } else {
                System.out.print(String.valueOf(c).toUpperCase());
            }
        }
    }
}