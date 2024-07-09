import java.io.*;
import java.util.*;

public class Main_1414 {
    static class Node implements Comparable<Node> {
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        parents = new int[N];
        for (int i = 0; i < N; i++) parents[i] = i;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int sum = 0;

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (chars[j] >= 'a' && chars[j] <= 'z') {
                    pq.offer(new Node(i, j, (int) chars[j] - 96));
                    sum += (int) chars[j] - 96;
                }
                if (chars[j] >= 'A' && chars[j] <= 'Z') {
                    pq.offer(new Node(i, j, (int) chars[j] - 38));
                    sum += (int) chars[j] - 38;
                }
            }
        }

        int cnt = 1;
        int lengthToUse = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int start = curr.start;
            int end = curr.end;
            int cost = curr.cost;

            if (find(start) != find(end)) {
                union(start, end);
                lengthToUse += cost;
                cnt++;
            }
        }

        // System.out.println(Arrays.toString(parents));

        if (cnt != N) System.out.println(-1);
        else System.out.println(sum - lengthToUse);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa < pb) parents[pb] = pa;
        else parents[pa] = pb;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
