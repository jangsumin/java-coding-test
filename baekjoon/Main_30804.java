import java.io.*;
import java.util.*;

public class Main_30804 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] sate = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) sate[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = 0, kind = 0, cnt = 0, max = 0;
        int[] num = new int[10];

        while (true) {
            if (right == N) break;

            if (num[sate[right]] == 0) kind++;

            cnt++;
            num[sate[right]]++;

            if (kind > 2) {
                if (--num[sate[left]] == 0) kind--;
                cnt--;
                left++;
            }

            max = Math.max(cnt, max);
            right++;
        }

        System.out.println(max);
    }
}
