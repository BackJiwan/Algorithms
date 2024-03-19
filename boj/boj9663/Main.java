package boj.boj9663;
/*N-Queen, 240319풀이시작
* 가능한 모든경우(완전탐색), 부분유지(재귀함수)
* 1.정답의 최대치 (int=-21억~21억)
* 2.복잡도 계산 (1초=1억번)
* 3.문제이해
* - 크기가 N * N인 체스판위에 퀸 놓기
* - 필수 : 퀸은 한행에 하나만 놓아질 수 있다. => 한 행에는 반드시 하나의 퀸을 놓을것 => 한행의 어느 위치에 퀸이 놓였는를 for로 순차 제시
* 4.구상A
* - Q를 놓기 위해서 row=1 부터 시작한다. rec_func은 row==N+1 일때가 되면 모든 퀸을 다 놓았다는 의미이기 때문에 return result++?
* - **중요한 개념** 퀸의 위치는 row에 종속된다. row를 알면 유일한 위치 하나가 특정되기 때문에 col[i]를 통해서 i자리에 row를 넣으면 내부에 저장되어 있는
*   col 값을 알 수 있다.
* 5.구현A
* - input()
*   N을 입력받는다. 이차원배열 int map[N+1][[N+1]을 초기화한다.
* - rec_func(int row) : row번째 row의 퀸을 고른다는 의미이다. 최초 실행시 1으로 시작해야 한다는 의미이다.
*   if row==N+1 이면 이미 N row의 퀸을 골랐고 문제가 없이 진행했기 때문에 cnt++ 를한다.
*   else
*       for(cand=1~N)을 통해서 어떤 col에 퀸을 위치시킬것인지 순차제시한다. (즉 매개변수로 row를 이동, 반복문으로 col을 이동)
*       먼저 cand가 골라지면 현재 row(row)의 col(cand)에 퀸을 놓을 것인데 놓을수 있는자리인지 (row,cand) 위치를 공격 가능한 인자가 있는지 찾고자
*           for(i는 1 ~ row-1) 까지 반복하며 현재 후보위치 윗 라인에 저장된 퀸의 위치들 중에서 내 후보위치를 공격 가능한  경우가 있는 지확인한다.
*           여기서 중요한 개념은 모든 퀸은 하나의 row에 하나의 col이 종속된다. 따라서 퀸의 위치를 col[i]에 저장하며 i는 row라고 볼 수 있다.
*           따라서 현재 for문의 i는 row를 의미하고 attackable(현재r,현재c,i,col[i])가 true 인 경우에만 가능성을 없애고 반복문의 상위로 올라간다.
*           if(가능성이 있는 경우) 현재 잡고 있는 row와 cand에 퀸을 넣고 rec_func(row+1)을 호출
* - attackable(r1,c1,r2,c2)
*   if c1 == c2
*   if r1-c1 == r2-c2
*   if r1+c1 == r2+c2
* */

import java.io.*;
import java.util.*;

public class Main {
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
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,ans;
    static int[] col; //각 인덱스 i에 해당하는 밸류는 row i에 놓인 퀸의 col 값이다.

    static void input(){
        N = sc.nextInt();
        col = new int[N+1]; //놓은 퀸을 기록하는 배열
        ans=0;
    }
    static void rec_func(int k){ //k는 row를 의미하며 1씩 아래로 내려간다. (재귀호출이용)
        if(k==N+1){ //N자리에 놓고 매개변수로 N+1을 보낸의미 이므로 이미 다 고른 경우
            ans++;
        }else{
            for(int cand=1;cand<=N;cand++){ //row는 재귀로 돌리고 있으니 col 값을 1부터 돌려서 골라야 한다.
                //이 자리에서 (k,cand)를 고른게 유효한지 체크, 유효하다면 col[i]에 기록 및 rec_func 호출 및 복구 까지 해야함
                boolean flag = true; //일단 놓아도 되는 자리라고 가정하고
                for(int i=1;i<=k-1;i++){ //지금까지 놓아진 col[i]를 순회하고자 1~직전row 까지 반복한다.
                    if (attackable(k,cand,i,col[i])){ //중간에 한번이라도 공격가능한 선조퀸을 찾았다면
                        flag = false;
                        break; //탈출명시하고 반복문 탈출
                    }
                }
                if (flag==true){ //딱히 날 공격할 선행 퀸이 없다면 현재위치에 퀸을 배치하고 재귀호출
                    col[k] = cand;
                    rec_func(k+1);
                    col[k] = 0; //썻으면 복구해주기
                }
            }
        }
    }
    static boolean attackable(int r1,int c1,int r2,int c2){
        if(c1==c2) return true;
        if(r1-c1 == r2-c2) return true;
        if(r1+c1 == r2+c2) return true;
        return false;
    }
    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(ans);
    }
}
