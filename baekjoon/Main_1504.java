import java.io.*;
import java.util.*;

public class Main_1504 {
    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N;
    static List<List<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향 이동 가능
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        // 반드시 거쳐야 할 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        if (getMinDistance(v1, v2) == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else if ((getMinDistance(1, v1) == Integer.MAX_VALUE || getMinDistance(v2, N) == Integer.MAX_VALUE) && (getMinDistance(1, v2) == Integer.MAX_VALUE || getMinDistance(v1, N) == Integer.MAX_VALUE)) {
            System.out.println(-1);
        } else {
            System.out.println(getMinDistance(v1, v2) + Math.min(getMinDistance(1, v1) + getMinDistance(v2, N), getMinDistance(1, v2) + getMinDistance(v1, N)));
        }
    }

    // 시작 정점에 따라 달라지는 최소 거리 구하기
    static int getMinDistance(int sv, int ev) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sv, 0));
        dist[sv] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            for (Node next : graph.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist[ev];
    }
}
