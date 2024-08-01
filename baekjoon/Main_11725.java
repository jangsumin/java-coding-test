import java.io.*;
import java.util.*;

public class Main_11725 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) list.add(new ArrayList<>());

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        // for (List<Integer> l : list) System.out.println(l.toString());

        int[] parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) parents[i] = i;

        boolean[] v = new boolean[N + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        v[1] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : list.get(curr)) {
                if (!v[next]) {
                    v[next] = true;
                    parents[next] = curr;
                    q.offer(next);
                }
            }
        }

        // System.out.println(Arrays.toString(parents));

        for (int i = 2; i < N + 1; i++) System.out.println(parents[i]);
    }

}
