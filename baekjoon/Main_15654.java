import java.io.*;
import java.util.*;

public class Main_15654 {
    static int N, M;
    static int[] numbers;
    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        selected = new int[M];
        visited = new boolean[N];

        Arrays.sort(numbers);

        permutation(0);
    }

    static void permutation(int n) {
        if (n == M) {
            for (int i = 0; i < M; i++) {
                if (i == M - 1) System.out.print(selected[i]);
                else System.out.print(selected[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[n] = numbers[i];
            permutation(n + 1);
            visited[i] = false;
        }
    }
}
