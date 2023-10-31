import java.io.*;
import java.util.*;

public class Main_알파벳회전초밥 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();

        int result = 1;
        int index = 0;
        next: while (true) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] == pattern[index]) {
                    index++;
                    if (index == pattern.length) break next;
                }
            }
            result += 1;
        }

        System.out.println(result);
    }
}
