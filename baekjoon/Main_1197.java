import java.io.*;
import java.util.*;

public class Main_1197 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] list = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list[A].add(new int[] {B, C});
            list[B].add(new int[] {A, C});
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        boolean[] v = new boolean[V + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {1, 0});

        int answer = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if (v[curr[0]]) continue;
            v[curr[0]] = true;

            answer += curr[1];

            for (int[] next : list[curr[0]]) {
                int index = next[0];
                int cost = next[1];

                if (dist[index] > cost) {
                    dist[index] = cost;
                    pq.offer(new int[] {index, cost});
                }
            }
        }

        // System.out.println(Arrays.toString(dist));
        System.out.println(answer);
    }
}
