package baekjoon;

import java.io.*;

class Main_10988 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split("");
   
    boolean isPalin = true;
    for (int i = 0; i < input.length / 2; i++)  {
      if (!(input[i].equals(input[input.length - 1 - i]))) {
        isPalin = false;
        break;
      }
    }

    if (isPalin) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }
}