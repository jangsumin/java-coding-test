package programmers;

class Main_스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (String skill_tree : skill_trees) {
            StringBuilder sb = new StringBuilder();
            for (char c : skill_tree.toCharArray()) {
                if (!(skill.indexOf(c) == -1)) sb.append(c);
            }
            if (skill.indexOf(sb.toString()) == 0) answer += 1;
        }
        
        return answer;
    }
}
