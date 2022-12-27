package boj.boj1182;
/*
* 1. N개의 정수, 수열, 공집합제외, 다 더한값이 S가 되는 수열
* 2. 수열을 하나씩 더하고 넘어가거나 안더하고 넘어가는 재귀함수
* 3. 수열에 끝에 도달하면 S와 같은지 확인하는 조건
* 4. 입력 :
* N S
* 수열(공백을 기준)
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1182_2 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, S, ans;
    static int[] seq;

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st==null|| !st.hasMoreElements()){
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
        S = scan.nextInt();
        seq = new int[N+1];
        for(int i=1;i<=N;i++){
            seq[i] = scan.nextInt();
        }
    }

    static void rec_func(int k, int value){
        if(k==N+1){
            if(value==S) ans++;
        }else{
            rec_func(k+1,value);
            rec_func(k+1,value+seq[k]);
        }
    }
    public static void main(String[] args){
        input();
        rec_func(1,0);
        if(S==0){
            ans--;
        }
        System.out.println(ans);
    }
}
