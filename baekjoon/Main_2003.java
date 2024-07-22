import java.io.*;
import java.util.*;

public class Main_2003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = 0, sum = 0, result = 0;
        while (true) {
            // System.out.println(start + " " + end);
            // System.out.println(sum);

            if (sum > M) {
                sum -= arr[start++];
            } else if (sum == M) {
                result++;
                sum -= arr[start++];
            } else if (end == N) {
                break;
            } else {
                sum += arr[end++];
            }
        }

        System.out.println(result);
    }
}
