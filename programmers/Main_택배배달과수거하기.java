package programmers;

class Main_택배배달과수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0, pickup = 0;
                                                                           
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] > 0 || pickups[i] > 0) {
                int cnt = 0;
                while (delivery < deliveries[i] || pickup < pickups[i]) {
                    cnt++;
                    delivery += cap;
                    pickup += cap;
                }
                delivery -= deliveries[i];
                pickup -= pickups[i];
                answer += 2 * (i + 1) * cnt;
            }
        }
        
        return answer;
    }
}
