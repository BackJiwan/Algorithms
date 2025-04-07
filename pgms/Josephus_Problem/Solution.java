package pgms.Josephus_Problem;

import java.util.ArrayDeque;

/*
*
* N명의 사람이 원형으로 서 있다. 각 사람은 1번부터 N번까지 번호표를 갖고 있으며, 임의의 숫자 K가 주어졌을 때 다음과 같은 규칙에 따라 사람을 제거한다:

1번 번호표를 가진 사람부터 시작하여 K번째 사람을 제거한다.

제거된 사람 다음 사람부터 다시 K번째 사람을 제거하는 과정을 반복한다.

이 과정을 통해 마지막까지 남는 사람의 번호를 구하라.
* */
import java.util.ArrayDeque;

public class Solution {

    // 문제 해결 함수 (내용은 비워둘 것)
    public int solution(int n, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i=1;i<n+1;i++){
            deque.addFirst(i);
        }

        while(deque.size()>1){

            for(int i=0;i<k-1;i++){
                int poll = deque.pollLast();
                deque.addFirst(poll);
            }
            deque.pollLast();
        }

        return deque.pollLast();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 테스트 케이스 1
        System.out.println("Test 1: " + (sol.solution(5, 2) == 3));

        // 테스트 케이스 2
        System.out.println("Test 2: " + (sol.solution(7, 3) == 4));  // 예상 결과는 4

        // 테스트 케이스 3
        System.out.println("Test 3: " + (sol.solution(10, 1) == 10));  // 매번 첫 사람 제거, 마지막 사람은 10번
    }
}
