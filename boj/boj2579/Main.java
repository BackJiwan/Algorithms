package boj.boj2579;
/*
* boj 2579 - 계단 오르기
* 문제 이해
* - 시작점은 계단에 포함하지 않는다.
* - 연속된 세계의 계단을 밟을 수 없다.
* - 만약 6번째 계단에 도착했을 경우 그 경로는
*   - i-1 번째 계단을 밟고 온 경우
*       - 이 경우라면 직전계단을 밟을수는 없다.
*   - 그렇지 않은 경우로 나뉜다.
*       - 이 경우라면 직전계단 또는, 2칸 전의 계단을 밟을 수 있따.
* d[1] = w[1]
* d[2] = 0 - 2 (건너 뜀)
*        0 -1-2(연속)
* d[3] = 0 - 1 - 3 (건너뜀)
 *       0 - 2- 3(연속)
* d[4] = 0 - 1 - 3 - 4(연속) - 건너뛰고 들어온 d[3]만 수용할 수 있다.
*        0 - 2 - 4(건너뜀) - d[2]꺼
*        0 -1-2 - (건너뜀)(연속) - d[2]꺼
* 즉 d[4] 부터는
* d[4][0](연속으로 들어옴)
 * d[4-1][1] 에서만 수용할 수 있다.
* d[4][1](건너뛰고 들어옴)
 * d[4-2][0] 과 d[4-2][1] 을 모두 수용할 수 있다.

* 점화식
* d4부터
* d[i][0] = d[i-1][1] + weight[i]
* d[i][1] = Math.max(d[i-2][0],d[i-2][1]) + weight[i]
* */

import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int weight[];
    static int score[][];

    static void input(){
        N = sc.nextInt();
        weight = new int[N+1];
        score = new int[N+1][2];
        for(int i=1;i<=N;i++){
            weight[i] = sc.nextInt();
        }
    }
    static void process(){
        // 초기 조건 설정
        if (N == 1) {
            System.out.println(weight[1]);
            return;
        } else if (N == 2) {
            System.out.println(Math.max(weight[1]+weight[2],weight[2]));
            return;
        } else if (N == 3) {
            System.out.println(Math.max(weight[2]+weight[3],weight[1]+weight[3]));
            return;
        }

        //연속으로 들어오면 0, 건너뛰고 들어오면 1
        score[1][0] = weight[1]; //1번 계단은 당연히 1번 가중치 뿐
        score[1][1] = 0;
        score[2][0] = weight[1]+weight[2];
        score[2][1] = weight[2];
        score[3][0] = 0+weight[2]+weight[3];
        score[3][1] = 0+weight[1]+weight[3];

        for(int i=4;i<=N;i++){
            score[i][0] = score[i-1][1] + weight[i];
            score[i][1] = Math.max(score[i-2][0],score[i-2][1])+weight[i];
        }

        System.out.println(Math.max(score[N][0],score[N][1]));
    }

    public static void main(String[] args){
        input();
        process();
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st==null||!st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLont(){
            return Long.parseLong(next());
        }
    }
}
