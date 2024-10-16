package acmicpc.완전탐색.boj15649_2;


import java.io.*;
import java.util.*;


public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static int N,M;
    static int[] selected;
    static int[] used;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M+1];
        used = new int[N+1];
    }

    static void rec_func(int k){
        //중복X , 순서O
        // 자리수
        if(k==M+1){
            for(int i=1;i<=M;i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        }else{
            for(int i=1;i<=N;i++){
                if(used[i]==1) continue;
                used[i] = 1;
                selected[k] = i;
                rec_func(k+1);
                used[i] = 0;
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
