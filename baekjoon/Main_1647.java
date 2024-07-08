import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main_1647 {
    static int sum = 0, cnt = 0, maxDist = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] v = new boolean[N + 1];

        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list.get(A).add(new Node(B, C));
            list.get(B).add(new Node(A, C));
        }

//        for (ArrayList<Node> el : list) {
//            for (Node node : el) System.out.print(node.index + " " + node.cost + " ");
//            System.out.println();
//        }

        dist[1] = 0; // Prim 알고리즘에서 어떤 정점을 시작으로 정해도 상관 없음
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if (v[curr.index]) continue;
            v[curr.index] = true;

            sum += curr.cost;
            maxDist = Math.max(maxDist, curr.cost);

            for (Node next : list.get(curr.index)) {
                if (dist[next.index] > next.cost) {
                    dist[next.index] = next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }

            if (++cnt == N) break; // 정점 개수의 -1 한 만큼 간선을 연결하면 모든 정점이 연결되었으므로 수행 안 함
        }

        // System.out.println(Arrays.toString(dist));
        System.out.println(sum - maxDist);
    }
}
