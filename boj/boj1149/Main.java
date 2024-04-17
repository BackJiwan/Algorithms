package boj.boj1149;
/*
* 1.정답의 최대치(-21억~21억=int)
* - 최대 정답은 1000개의 집에 1000 비용이 모두 소요된다. => 1,000,000 = 1백만 int 가능 !
* 2.복잡도 계산(1초:1억번, 0.5초:5천만번)(완탐)
* - 입력받는데에 O(n)이 소요된다.
* - 완전탐색을 위해서 재귀방식으로 호출할 경우 가장 처음 3, 이후 남은 집의 개수 (n-1)만큼 2^n 계산이 반복된다.
*   (3*2^(n-1))
* 3. 읽으면서 계산하는 방식은 N으로 끝남 사실 시간 복잡도는 1/2 수준인거 같은데 공간복잡도가 조금 더 이득일라나..?
* 3.문제이해
* - 집이 N개, 거리는 선분, 1~N 집까지 순서대로
* - 집은 R,G,B 중 하나로 칠해진다. 색마다 비용이 다르다.
* - 모든 집을 칠하는 비용의 최솟값
* - (제약조건)i번집의 색은 i-1, i+1의 집 색과 같지 않아야 한다.
*
* 4.구상A
* */
import java.io.*;
import java.util.*;
import java.lang.Math;

public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] D;
    static int[] A;

    static void input() {
        N = sc.nextInt();
        A = new int[3];
        D = new int[N + 1][3];

    }

    static void pro() {
        // 시작값
        D[0][0]=D[0][1]=D[0][2]=0;

        //계산하면서 D 배열 채우기
        //점화식...? D[i][j] = i 번째 집을 칠하기 위해서 j 색으로 칠할경우 가능한 최솟값이 기록됨
        for(int i=1;i<N+1;i++){
            for(int j=0;j<3;j++){
                A[j] = sc.nextInt(); //임시로 해당 집의 각 색깔별 가격 입력받기
            }
            //0번을 칠하려면 직전에 1번 or 2번을 칠한 경우일텐데
            //이번에 0번을 칠하려면 1과2중 작은 값에 0값 더하기
            D[i][0] = Math.min(D[i - 1][1], D[i - 1][2]) + A[0];
            //이번에 1번을 칠하려면 0과2중 작은 값에 1값 더하기
            D[i][1] = Math.min(D[i - 1][0], D[i - 1][2]) + A[1];
            //이번에 2번을 칠하려면 1과2중 작은 값에 2 더하기
            D[i][2] = Math.min(D[i - 1][0], D[i - 1][1]) + A[2];
        }

        //정답 찾기
        System.out.println(Math.min(D[N][0], Math.min(D[N][1], D[N][2])));
    }

    public static void main(String[] args) {
        double take1 = 3.0*Math.pow(2,999);
        System.out.println("완전탐색의 시간복잡도 : "+take1);
        double take2 = 1000.0;
        System.out.println("DP방식의 시간복잡도 : "+take2);
        input();
        pro();
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