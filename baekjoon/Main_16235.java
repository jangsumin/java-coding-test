package baekjoon;

import java.io.*;
import java.util.*;

class Tree implements Comparable<Tree> {
  int x;
  int y;
  int age;

  public Tree(int x, int y, int age) {
    this.x = x;
    this.y = y;
    this.age = age;
  }

  public int compareTo(Tree o) {
    return this.age - o.age;
  }
}

public class Main_16235 {
  static int N, M, K;
  static int[][] add, cur;
  static Queue<Tree> trees = new PriorityQueue<>();
  static Queue<Tree> deadTrees = new ArrayDeque<>();
  static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    add = new int[N][N]; // 매년 추가되는 양분 저장
    cur = new int[N][N]; // 각 칸의 양분 상태
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < N; j++) {
        add[i][j] = Integer.parseInt(st.nextToken());
        cur[i][j] = 5;
      }
    }
    
    int x, y, z;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      x = Integer.parseInt(st.nextToken()) - 1; // (1, 1)이 맨 위, 맨 왼쪽 좌표
      y = Integer.parseInt(st.nextToken()) - 1;
      z = Integer.parseInt(st.nextToken());
      trees.offer(new Tree(x, y, z));
    }

    // for (int[] row : cur) System.out.println(Arrays.toString(row));

    // for (Tree tree : trees) {
    //   System.out.println(tree.x + " " + tree.y + " " + tree.age);
    // }

    while (K-- > 0) {
      spring();
      summer();
      autumn();
      winter();
    }

    System.out.println(trees.size());
  }

  static void spring() {
    Queue<Tree> tmp = new PriorityQueue<>(); // 임시 큐에 딤아두기
    
    while (!trees.isEmpty()) {
      Tree t = trees.poll();
      if (cur[t.x][t.y] >= t.age) {
        cur[t.x][t.y] -= t.age;
        t.age += 1;
        tmp.offer(t);
      } else {
        deadTrees.offer(t);
      }
    }
    trees = tmp;
    // for (Tree tree : trees) {
    //   System.out.println("봄 끝 " + tree.x + " " + tree.y + " " + tree.age);
    // }
  }

  static void summer() {
    while (!deadTrees.isEmpty()) {
      Tree t = deadTrees.poll();
      cur[t.x][t.y] += t.age / 2;
    }
  }

  static void autumn() {
    Queue<Tree> tmp = new PriorityQueue<>();
    for (Tree tree : trees) tmp.offer(tree);
    
    while (!trees.isEmpty()) {
      Tree t = trees.poll();
      int x = t.x;
      int y = t.y;
      if (t.age % 5 == 0) {
        for (int i = 0; i < 8; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            tmp.offer(new Tree(nx, ny, 1));
          }
        }
      }
    }
    trees = tmp;
    // for (Tree tree : trees) {
    //   System.out.println("가을 끝 " + tree.x + " " + tree.y + " " + tree.age);
    // }
  }

  static void winter() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        cur[i][j] += add[i][j];
      }
    }
  }

}
