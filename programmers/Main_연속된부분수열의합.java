package programmers;

class Main_연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int left = 0, right = n;
        int sum = 0;
        
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && sum < k) {
                sum += sequence[r++];
            }
            if (sum == k) {
                int range = r - l - 1;
                if (range < right - left) {
                    left = l;
                    right = r - 1;
                }
            }
            sum -= sequence[l];
        }
        
        int[] answer = {left, right};
        return answer;
    }
}
