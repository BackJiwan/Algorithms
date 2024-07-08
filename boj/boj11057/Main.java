package boj.boj11057;

/* 오르막수
* 문제 이해:
* - 수의 길이 N이 주어졌을 때, 오르막수의 개수를 구하기
* - 수는 0으로 시작할 수 있다.
* - 10,007로 나눈 나머지를 출력하기
* - 길이는 1~1000 까지
* d[1] : 0,1,2,3,4,5,6,7,8,9        => 10개
* d[2] : 00,                        -1
*        01, 11                     -2
*        02, 12, 22                 -3
*       ...
*       09,19,29,39,49,59,69,79,89,99 - 10 => (10*11)/2 = 55개
* d[3] : 000,                       -1
*       001,011,111                 -3
*       002,
*       (10*11*12)/3 = 11
* - 다시 생각해보기
*   - dy[len][num] = 길이가 len이고 num으로 끝나는 오르막수의 개수를 카운트 한다.
*   dy[1][0~9] : 길이가 1이고 0~9 까지 끝나는 수들 이므로 다 1 * 10 으로 총 10개가 된다. -> 모두 1로 초기화 시켜둔다.
*   파티셔닝을 해보면 dy[len][num]을 구하기 위해서는
*   dy[len-1][0] ~ dy[len-1][num] 까지를 각각 더한 값이다.
* */

import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();

    static int N;
    static int[][] Dy;

    static void input(){
        N = sc.nextInt();
        Dy = new int[N+1][10];
    }

    static void process(){
        //Dy[1][num=0~9] 까지 초기값 설정하기
        for(int i=0;i<=9;i++){
            Dy[1][i] = 1;
        }

        //설정된 초기값을 바탕으로
        //dy[2~len][num=0~9]까지 채워주기
        for(int len=2;len<=N;len++){
            for(int num=0;num<=9;num++){
                for(int prev = 0;prev<=num;prev++){
                    Dy[len][num] += Dy[len-1][prev];
                    Dy[len][num] %= 10007;
                }
            }
        }

        //길이가 N인 오르막수를 구하기 위해서
        //ans += dy[N][0~9]의 합산을 더해서 구하기
        int ans = 0;
        for(int i=0;i<=9;i++){
            ans += Dy[N][i];
            ans %= 10007;
        }
        System.out.println(ans);

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
            while(st==null || !st.hasMoreElements()){
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


    }
}
