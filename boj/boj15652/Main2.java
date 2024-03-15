package boj.boj15652;
/* 완탐(범위가 작다), 재귀호출(부분유지,리턴 하고 새로운시도 필요)
* 1.정답의 최대치 (-21억~21억=int)
* - N=8일 때에 8, int 사용
* 2.복잡도 계산(1초, 1억번)
* - 각 자리마다 N개중 하나를 고르는 행위를 M번 해야 한다. 그러나 앞에 쓰인것보단 작으니까 N^M보단 작다.
* 3.구현
* - 비내림차순 -> 정답에 사용할 selected 배열에 사용된 가장 큰수를 지속적으로 갱신해줘야 한다.
*   -> 가장 큰수를 굳이 살필 필요 없이, 항상 적어도 직전 자리수 보다는 커야함을 지켜도 된다.
*   -> 직전 자리수에 사용할 int prev는 static
* - 중복 허용 -> 굳이 사용된 숫자를 기록할 필요는 없다.
*
* 4.상세
* input()
* - N과 M 입력받기
* - selected[M+1] 초기화
* - 직전 사용한 수 int prev
* rec_func(int k)
* - if k가 M+1 이면 sb에 selected[]를 append한다.
*   else
* - for cand=1~N 까지 증가하며 selected에 넣을 숫자 고르기
*   if 고른 cand가 직전 자리수 prev보다 작으면 continue
*   else 직전자리수 prev를 cand로 갱신, selected[k]=cand, rec_func(k+1)재귀호출,prev=0,selected[k]=0 돌리기
* */

import java.io.*;
import java.util.*;

public class Main2 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

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

    static int N,M,prev;
    static int[] selected;
    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        prev = 0;
        selected = new int[M+1];
    }

    static void rec_func(int k){
        if(k==M+1){
            for(int i=1;i<M+1;i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }else{
            for(int cand=1;cand<N+1;cand++){
                if(cand<prev){
                    continue;
                }else{
                    prev = cand;
                    selected[k] = cand;
                    rec_func(k+1);
                    prev = 0;
                    selected[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(sb);
    }

}
