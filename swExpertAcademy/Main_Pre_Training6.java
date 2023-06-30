// 창용 마을 무리의 개수
package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Main_Pre_Training6 {
  static int n, m;
  static boolean[] visited;
  static ArrayList<Integer>[] node;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());

    for (int i = 1; i <= tc; i++) {
      String[] info = br.readLine().split(" ");
      n = Integer.parseInt(info[0]);
      m = Integer.parseInt(info[1]);

      /* 
        [[0, 1, 0, 0, 1, 0], [1, 0, 0, 0, 1, 0], [0, 0, 0, 1, 0, 0],
        [0, 0, 1, 0, 0, 1], [1, 1, 0, 0, 0, 0], [0, 0, 0, 1, 0, 0]]
      */
      visited = new boolean[n + 1];
      node = new ArrayList[n + 1];
      for (int j = 1; j <= n; j++) {
        node[j] = new ArrayList<Integer>();
      }

      for (int j = 0; j < m; j++) {
        String[] relation = br.readLine().split(" ");
        node[Integer.parseInt(relation[0])].add(Integer.parseInt(relation[1]));
        node[Integer.parseInt(relation[1])].add(Integer.parseInt(relation[0]));
      }

      int relation_cnt = 0;
      for (int j = 1; j <= n; j++) {
        if (visited[j]) {
          continue;
        }
        dfs(j);
        relation_cnt++;
      }

      System.out.println(String.format("%s%d %d", "#", i, relation_cnt));
    }
  }

  static void dfs(int cur) {
    visited[cur] = true;
    for (int next : node[cur]) {
      if (visited[next]) {
        continue;
      }
      dfs(next);
    }
  }
}