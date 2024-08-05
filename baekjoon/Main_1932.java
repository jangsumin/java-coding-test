import java.io.*;
import java.util.*;

public class Main_1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] triangle = new int[n][n];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < i; j++) {
                triangle[i - 1][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) triangle[i][j] += triangle[i - 1][j];
                else if (j == i) triangle[i][j] += triangle[i - 1][j - 1];
                else triangle[i][j] += Math.max(triangle[i - 1][j], triangle[i - 1][j - 1]);
            }
        }

        int result = 0;
        for (int el : triangle[n - 1]) result = Math.max(result, el);

        System.out.println(result);
    }
}
