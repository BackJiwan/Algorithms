package acmicpc.완전탐색.boj15651_2;
//중복을 허용해서, 순서있게 나열까찌
//nPm

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

        public FastReader(String s) throws FileNotFoundException {
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

    static void rec_func(int k){
        //수열의 k 번째 숫자를 고르는 재귀함수
        if(k==M+1){ //만약 M+1번째 자리수를 고르는 호출이라면 이미 selected는 1~M 까지 가득 차있으므로 sb에 추가해주면 된다.
            for(int i=1;i<=M;i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        }else{
            //여기에서는 k 번째 자리에 1~N 까지의 모든 수를 하나씩 호출해주어야 한다.
            for(int i=1;i<=N;i++){
                selected[k] = i;
                rec_func(k+1);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }
}
