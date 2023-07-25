// Lv1

package programmers;

class Main_덧칠하기 {
    public int solution(int n, int m, int[] section) {
        int result = 0;
        result++;
        int start = section[0];
        
        for (int el : section) {
            if (el < start + m) continue;
            start = el;
            result++;
        }
        
        return result;
    }
}
