package boj.boj15650;
/* 완전탐색(작은범위), 재귀함수(부분유지) -> 백트래킹(가지치기 있으면 좋다)
* 1.정답의 최대치(int=-21억~21억) : int가능
* -최대 N=8일때, 8담기는 경우 가장 큰 수이다.
* 2.복잡도(1초=1억번)
* -재귀적으로 모든 경우를 탐색, 직전수보다는 항상 클것이기에 N,N-1,N-2,... 과 깉이 경우가 하나씩 줄어든다.
* 3.구현
* input()
* -N,M을 입력받는다. 정답에 사용될 selected[M+1] 초기화,직전에 사용된 수를 인덱스로 접근해서 1로 체크해두는 check[N+1] 배열 초기화
* XXXrec_func(int k)
* XXX- 1.만약 k가 M+1 이면 직전에 1~M까지 모두 배열을 채운 상태 이므로 반복문을 통해 selected의 모든 요소를 sb에 append
* XXX- else(1) for(cand=1~N) k자리에 입력할 수를 찾기 위해 for 반복문을 돌린다. (차라리 rec_func 호출시 직전 사용한 값을 인자로 주고 그거 이상부터 돌리는게 낫겟다)
* rec_func(int k,int prev)
 * -1.만약 k가 M+1 이면 직전에 1~M까지 모두 배열을 채운 상태 이므로 반복문을 통해 selected의 모든 요소를 sb에 append
 * -else(1) for(cand=(prev+1)~N) k자리에 입력할 수를 찾기 위해 for 반복문을 돌린다.직전사용한 prev보다는 커야 한다.
 *  selected[k]=cand 대입연산, rec_func(k+1,cand)로 호출,호출 끝났으면 selected[k]=0 으로 복구,
*
* */

import shinhan_ds.ch06.mini_project1.Player;

import java.io.*;
import java.util.*;

public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws IOException{
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
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }


    }

    static int N,M;
    static int[] selected;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M+1];
    }

    static void rec_func(int k,int prev){
        if(k==M+1){
            for(int i=1;i<M+1;i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }else{
            for(int cand=prev+1;cand<N+1;cand++){
                selected[k] = cand;
                rec_func(k+1,cand);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1,0);
        System.out.println(sb);
    }
}
