package boj.boj15650;

import java.io.*;
import java.util.*;

/*
* 230824 문제풀이
* 자연수 N과 M 입력받기
* 핵심 : 길이가 M인 수열을 모두 출력, 1~N 의 숫자를 사용, 중복X, 오름차순
* 머리 : 정답을 저장하는 int arr[M] 을 static 사용, 재귀중인 함수에게 자리수를 알려주는 cnt를 매개변수로 전달,직전에 사용한 수 prev 전달
* cnt는 0부터 시작 M미만일때만 재귀호출, rec_func(cnt,prev) 부터 시작한다.
* rec_func은 시작과 함께 만약 cnt=>M-1이면 for 문으로 들어와서 arr[cnt] 자리에 int n은 (prev+1)~N까지 가능하며 회차마다 sb에
* arr[cnt] = n으로 저장한다.
* */
public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int[] arr;

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[M+1];
    }

    static void rec_func(int prev,int cnt){
        if(cnt>=M){
            for(int j=0;j<M;j++){
                sb.append(arr[j]).append(" ");
            }
            sb.append('\n');
            return;
        }
        for(int k=prev;k<N;k++){
            arr[cnt]=k+1;
            rec_func(k+1,cnt+1);
        }
    }

    public static void main(String[] args){
        input();
        rec_func(0,0);
        System.out.println(sb);
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

        double nextDouble(){
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str ="";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
}
