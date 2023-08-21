package programmers;

class Solution_타겟넘버 {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    static void dfs(int cnt, int sum, int[] arr, int target) {
        if (cnt == arr.length) {
            if (sum == target) answer++;
            return;
        }
        
        dfs(cnt + 1, sum + arr[cnt], arr, target);
        dfs(cnt + 1, sum - arr[cnt], arr, target);
    }
}
