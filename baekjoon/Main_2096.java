import java.io.*;
import java.util.*;

public class Main_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] max = new int[3];
        int[] min = new int[3];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                max[0] = min[0] = a;
                max[1] = min[1] = b;
                max[2] = min[2] = c;
            } else {
                int prevMaxZero = max[0], prevMaxTwo = max[2];
                max[0] = Math.max(max[0], max[1]) + a;
                max[2] = Math.max(max[1], max[2]) + c;
                max[1] = Math.max(Math.max(prevMaxZero, prevMaxTwo), max[1]) + b;

                int prevMinZero = min[0], prevMinTwo = min[2];
                min[0] = Math.min(min[0], min[1]) + a;
                min[2] = Math.min(min[1], min[2]) + c;
                min[1] = Math.min(Math.min(prevMinZero, prevMinTwo), min[1]) + b;
            }
        }

        int maxSum = 0;
        for (int i = 0; i < 3; i++) maxSum = Math.max(maxSum, max[i]);

        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) minSum = Math.min(minSum, min[i]);

        System.out.println(maxSum + " " + minSum);
    }
}
