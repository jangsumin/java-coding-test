import java.io.*;
import java.util.*;

public class Main_1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = 0, sum = 0, cnt = 0, min = Integer.MAX_VALUE;

        // 주어진 숫자는 모두 양수이므로 S 값을 넘는 순간 중지한다.
        // 가장 짧은 것의 길이를 구하고 싶으므로 더 이상 더할 필요가 없다.

        while (true) {
            // System.out.println(sum);

            if (sum >= S) {
                min = Math.min(min, cnt);
                sum -= nums[left];
                cnt--;
                left++;
            } else if (right == N) {
                break;
            } else {
                sum += nums[right];
                cnt++;
                right++;
            }
        }

        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}
