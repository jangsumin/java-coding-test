package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2156 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] glasses = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            glasses[i] = Integer.parseInt(br.readLine());
        }

        // System.out.println(Arrays.toString(glasses));

        int[] dp = new int[n + 1];
        dp[1] = glasses[1];
        if (n > 1) {
            dp[2] = glasses[1] + glasses[2];
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + glasses[i], dp[i - 3] + glasses[i - 1] + glasses[i]));
        }

        System.out.println(dp[n]);
    }
}
