package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1062 {
  static int N, K;
  static String[] words;
  static boolean[] v = new boolean[26];
  static int result = 0;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    words = new String[N];
    for (int i = 0; i < N; i++) {
      String word = br.readLine();
      word.replaceAll("a", ""); 
      word.replaceAll("c", ""); 
      word.replaceAll("i", ""); 
      word.replaceAll("n", ""); 
      word.replaceAll("t", ""); 
      words[i] = word;
    }

    v['a' - 'a'] = true;
    v['c' - 'a'] = true;
    v['i' - 'a'] = true;
    v['n' - 'a'] = true;
    v['t' - 'a'] = true;

    // System.out.println(Arrays.toString(words));

    // K가 5보다 작으면 무조건 0이 답

    if (K < 5) {
      System.out.println(0);
      return;
    } else if (K == 26) {
      System.out.println(N);
      return;
    }

    K -= 5;
    dfs(0, 0);
    System.out.println(result);
  }

  static void dfs(int idx, int cnt) {
    if (cnt == K) {
      int count = 0;

      for (int i = 0; i < N; i++) {
        boolean isReadable = true;
        for (int j = 0; j < words[i].length(); j++) {
          if (!v[words[i].charAt(j) - 'a']) {
            isReadable = false;
            break;
          }
        }

        if (isReadable) count++;
      }

      result = Math.max(result, count);
      return;
    }

    for (int i = idx; i < 26; i++) {
      if (v[i]) continue;

      v[i] = true;
      dfs(i, cnt + 1);
      v[i] = false;
    }
  }

}