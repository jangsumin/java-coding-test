import java.io.*;
import java.util.*;

public class Main_1446 {
    static class Node {
        int to;
        int length;

        Node(int to, int length) {
            this.to = to;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= 10001; i++) graph.add(new ArrayList<>());

        int[] dist = new int[10001];
        for (int i = 0; i <= 10000; i++) dist[i] = i;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, l));
        }

        for (int i = 0; i <= D; i++) {
            if (dist[i + 1] > dist[i] + 1) dist[i + 1] = dist[i] + 1;

            for (int j = 0; j < graph.get(i).size(); j++) {
                if (dist[i] + graph.get(i).get(j).length < dist[graph.get(i).get(j).to]) {
                    dist[graph.get(i).get(j).to] = dist[i] + graph.get(i).get(j).length;
                }
            }
        }

        System.out.println(dist[D]);
    }
}
