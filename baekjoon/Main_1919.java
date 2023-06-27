package baekjoon;
import java.io.*;
import java.util.*;

// 애너그램 만들기
public class Main_1919 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str1 = br.readLine();
    String str2 = br.readLine();

    int anagram_cnt = 0;
    char[] arr1 = str1.toCharArray();
    char[] arr2 = str2.toCharArray();
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    int pointer = 0;
    for (int i = 0; i < str1.length(); i++) {
      for (int j = pointer; j < str2.length(); j++) {
        if (arr1[i] == arr2[j]) {
          pointer = j + 1;
          anagram_cnt += 1;
          break;
        }
      }
    }

    System.out.println(str1.length() + str2.length() - 2 * anagram_cnt);
  }
}