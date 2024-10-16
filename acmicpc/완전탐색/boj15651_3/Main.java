package acmicpc.완전탐색.boj15651_3;
/*
*
* */
import java.io.*;
import java.util.*;

public class Main {
    static FastReader sc = new FastReader();
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
    static int N,M;
    static int[] arr;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M+1];
    }

    static void rec_func(int k){
        //if k==M+1 이면 sb에 append
        if(k==M+1){
            for(int i=1;i<=M;i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
        }else{
            // else 의 경우라면
            for(int i=1;i<=N;i++){
                arr[k] = i;
                rec_func(k+1);
                arr[k] = 0;
            }
        }
    }
    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }
}
