import java.io.*;
import java.util.*;

public class Main_1043 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int cnt = Integer.parseInt(st.nextToken());

        // 진실을 알고 있는 사람의 배열
        int[] knows = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            knows[i] = Integer.parseInt(st.nextToken());
        }

        // 파티 정보 기억하는 리스트
        ArrayList<ArrayList<Integer>> info = new ArrayList<>();
        for (int i = 0; i < M; i++) info.add(new ArrayList<>());

        // 진실을 알고 있는 사람과 직접 만난 사람들의 리스트
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int participantsCnt = Integer.parseInt(st.nextToken());

            int[] participants = new int[participantsCnt];
            for (int j = 0; j < participantsCnt; j++) {
                participants[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < participantsCnt; j++) {
                info.get(i).add(participants[j]);
            }

            for (int j = 0; j < participantsCnt; j++) {
                for (int k = 0; k < participantsCnt; k++) {
                    if (j == k) continue;

                    if (!list.get(participants[j]).contains(participants[k])) {
                        list.get(participants[j]).add(participants[k]);
                    }
                }
            }
        }

        // for (ArrayList<Integer> el : info) System.out.println(el.toString());

        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < cnt; i++) {
            if (visited[knows[i]]) continue;

            visited[knows[i]] = true;
            q.offer(knows[i]);

            while (!q.isEmpty()) {
                int curr = q.poll();

                for (int next : list.get(curr)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }

        // System.out.println(Arrays.toString(visited));

        int result = M;
        for (ArrayList<Integer> el : info) {
            for (int number : el) {
                if (visited[number]) {
                    result--;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
