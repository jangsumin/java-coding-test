// 두 개의 숫자열(D2)
package swExpertAcademy;
import java.io.*;
import java.util.*;

public class Main_Pre_Training1 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 0; i < tc; i++) {
      String[] split_n_m = br.readLine().split(" ");
      String[] n = br.readLine().split(" ");
      String[] m = br.readLine().split(" ");

      String[] standard;
      String[] move;
      if (Integer.parseInt(split_n_m[0]) >= Integer.parseInt(split_n_m[1])) {
        standard = n;
        move = m;
      }
      else {
        standard = m;
        move = n;
      }
      int[] sum = new int[standard.length - move.length + 1];
      for (int j = 0; j <= standard.length - move.length; j++) {
        for (int k = 0; k < move.length; k++) {
          sum[j] += Integer.parseInt(standard[j + k]) * Integer.parseInt(move[k]);
        }
      }
      
      System.out.println(String.format("%s%d %d", "#", i + 1, Arrays.stream(sum).max().getAsInt()));
    }
  }
}