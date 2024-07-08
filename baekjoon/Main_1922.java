import java.io.*;
import java.util.*;

// 크루스칼 알고리즘
// 그래프의 집합을 구하는 데 용이한 알고리즘

public class Main_1922 {
    static int[] parents;
    static int minD = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;

        int[][] info = new int[M][3];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
            info[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(info, (a, b) -> a[2] - b[2]);
        // for (int[] el : info) System.out.println(Arrays.toString(el));

        for (int i = 0; i < M; i++) {
            int a = info[i][0];
            int b = info[i][1];
            int c = info[i][2];

            if (find(a) != find(b)) {
                union(a, b);
                minD += c;
            }
        }

        System.out.println(minD);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parents[pa] = pb;
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}
