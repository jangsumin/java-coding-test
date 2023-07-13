// 원재의 메모리 복구하기(D3)
package swExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main_1289 {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            String[] input = br.readLine().split("");
            int[] memory = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                memory[i] = Integer.parseInt(input[i]);
            }
             
            int result = 0;
            boolean change = false;
            for (int i = 0; i < input.length; i++) {
                if (memory[i] == 1 && change == false) {
                    result += 1;
                    change = true;
                }
                if (memory[i] == 0 && change == true) {
                    result += 1;
                    change = false;
                }
            }
             
            System.out.println(String.format("%s%d %d", "#", tc, result));
        }
    }
}