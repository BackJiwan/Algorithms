package boj.boj13144;

/*
 * 0. 경우의 수인데 각 부분배열의 길이를 활용하는 이유는 길이 3인 배열이라면
 *    L을 기준으로 [L],[L,L+1],[L,L+1,L+2] 이와 같이 길이와 동일한 부분집합의 개수가 도출되기 때문이다.
 * 1. 정답의 최대치? 수열의 한칸에는 최대 10만이 들어갈수 있다.
 * 2. 수열의 길이는 최대 10만이다.
 * 3. 10만자리의 수열이 모두 다른숫자라면 최대 정답이 나올수 있다.
 * 4. 경우의 수는 L=1일떄 10만, L=2일때 10만-1....L=10만일때 1의 식이므로
 *    N(N-1)/2 => 10만^2 /2 => 5,000,000,000 => 50억 (int=최대 20억)
 * 5. 정답을 담는 ans는 long으로 선언해야 함
 * 6. 1~10만의 index를 가지는 int 배열 cnt를 만들고 수열을 탐색하다가 등장하는 숫자는 1증가시킨다.
 * 7. L=1부터 수열의 size까지 반복하며 1씩 증가한다.
 * 8. 내부에서는 R의 다음행보를 기준으로(R+1) N이하인지, 이미 등장한적없는지를 교차검증하고 R = R+1을 대입하고 카운트배열을 증가시켜준다.
 * 9. 8번의 while 문이 종료되면 기준L에서 최대한 갈수 있는 R까지만 이동한 경우이기 때문에 ans += R -L +1 을 저장해준다.
 * 10. 그 뒤 사용된 L의 정보를 폐기하기 위해서 cnt[arr[L]] -= 1 을 실행해준다.
 * */

import java.io.*;
import java.util.*;

public class Main3 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A, cnt;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        cnt = new int[100000 + 1];
    }

    static void pro() {
        long ans = 0;

        for (int L=1, R=0; L<=N; L++){  // L 마다 R 을 최대한 옮겨 줄 계획이다.
            // R 을 옮길 수 있는 만큼 옮긴다.
            while (R + 1 <= N && cnt[A[R+1]] == 0){ //다음 R을 옮겨도 중복되지 않는다면
                R++; //R을 하나더 옮겨주고
                cnt[A[R]]++; //사용된 번호카운트를 증가 시켜줌
            }

            // 정답을 갱신한다
            ans += R - L + 1;

            // L 을 옮겨주면서 A[L] 의 카운트 개수를 감소시킨다.
            cnt[A[L]]--;
        }

        System.out.println(ans);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}