// 백준 - 스위치 켜고 끄기
package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1244 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchCnt = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] switchState = new int[switchCnt];
		for (int i = 0; i < switchCnt; i++) switchState[i] = Integer.parseInt(st.nextToken());
		
		int studentCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < studentCnt; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			
			int gender = Integer.parseInt(st1.nextToken());
			int location = Integer.parseInt(st1.nextToken());
			
			// 남자일 때
			if (gender == 1) {
				int mul = 1;
				while (true) {
					if (location * mul - 1 > switchCnt - 1) break;
					switchState[location * mul - 1] = switchState[location * mul - 1] == 0 ? 1 : 0;
					mul += 1;
				}
			}
			
			// 여자일 때
			if (gender == 2) {
				int base = location - 1;
				switchState[base] = switchState[base] == 0 ? 1 : 0;
				int move = 1;
				while (true) {
					if (base - move < 0 || base + move > switchCnt - 1) break;
					if (switchState[base - move] == switchState[base + move]) {
						switchState[base - move] = switchState[base + move] = switchState[base + move] == 0 ? 1 : 0;
						move += 1;
					} else {
						break;
					}
				}
			}
		}
		
		// studentInfo 순회하면서 0번째 원소가 1이면 남학생, 2이면 여학생
		// 남학생의 경우에는 단순히 switchState 배열 순회하면서 상태만 바꾸면 됨.
		// 여학생의 경우에는 해당 인덱스를 기준으로 앞 뒤로 얼마나 같은지 확인해보기
		// ㄴ 그러다가 인덱스 범위 벗어나면 그냥 거기까지만
		// ㄴ 허용하는 범위 내의 스위치 상태 변경
	
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i = 0; i < switchCnt; i++)  {
			if (cnt == 20) {
				sb.append('\n');
				cnt = 0;
			}
			if (i != switchCnt - 1) sb.append(switchState[i]).append(' ');
			else sb.append(switchState[i]);
			cnt += 1;
		}
		System.out.println(sb.toString());
	}
}
