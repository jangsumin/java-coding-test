import java.io.*;
import java.util.*;

public class Main_1238 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 만약 마을 간의 거리를 인접 행렬에 저장한다면 N^2의 공간을 차지하게 되므로 1,000,000 크기를 점유
        // 만약 인접 리스트에 저장한다면 10,000 크기를 점유

        ArrayList<int[]>[] list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[start].add(new int[] {end, cost});
        }

        int[] roundTrips = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            boolean[] v = new boolean[N + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

            dist[i] = 0;
            pq.offer(new int[] {i, 0});

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();

                if (v[curr[0]]) continue;
                v[curr[0]] = true;

                for (int[] next : list[curr[0]]) {
                    int index = next[0];
                    int cost = next[1];

                    if (dist[index] > curr[1] + cost) {
                        dist[index] = curr[1] + cost;
                        pq.offer(new int[] {index, dist[index]});
                    }
                }
            }

            if (i == X) {
                for (int j = 1; j <= N; j++) {
                    roundTrips[j] += dist[j];
                }
            } else {
                roundTrips[i] += dist[X];
            }
        }

        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            maxDistance = Math.max(maxDistance, roundTrips[i]);
        }
        System.out.println(maxDistance);
    }
}
