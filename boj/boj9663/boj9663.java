package boj.boj9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 1. 입력 : N
* 2. 체스판 : N * N
* 3. N개의 퀸이 서로 공격할 수 없는 경우의 수를 출력한다.
* rec_func : row를 매개변수로 받는다 이것은 1행 부터 시작해서 한행에 하나의 퀸을 배정하는 것이다.
* 복잡도를 줄이는 백트래킹을 적용하기 위해서 퀸을 놓기 이전에 피격당하지 않는 위치인지를 파악한다.
* 따라서 재귀함수의 끝에 닿았을때 = row 가 N일때 ans를 1증가 하면된다.
* 퀸의 배치는 1 row부터 순차적으로 내려가기 때문에 나보다 위에 있는 row 들에 대해서만 공격가능 검사를 돌리면된다.
* 또한 퀸은 하나의 row에 하나밖에 존재하지 않기 때문에 해당 row에 존재하는 퀸의 좌표를 col[row] = 몇 열? 인지로 저장해둔다.
* 공격가능한지 판단하기 위해서는 나와 같은 col에 있는지 + (행-열)이 같은지 + (행+열)이 같은지를 고려한다.
* */
public class boj9663 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st==null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }

    static void input(){
        N = scan.nextInt();
        col = new int[N+1];
    }

    static int N, ans;
    static int[] col;

    static boolean attackable(int r1,int c1,int r2,int c2){
        if(c1 == c2)
            return true;
        else if((r1-c1)==(r2-c2))
            return true;
        else if((r1+c1)==(r2+c2))
            return true;
        else
            return false;
    }

    static void rec_func(int row){
        if(row == N+1){
            ans++;
        } else{
            for(int c=1;c<=N;c++){ //주어진 row에서 행을 한칸씩 이동하면서 체크
                boolean possible = true;
                for(int i=1;i<=row;i++){
                    if(attackable(row,c,i,col[i])){ //한번이라도 공격가능성을 체크하면 false로 바꾸고 break
                        possible = false;
                        break;
                    }
                }
                if(possible){
                    col[row] = c;
                    rec_func(row+1);
                    col[row] = 0;
                }
            }
        }
    }
    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(ans);
    }
}
