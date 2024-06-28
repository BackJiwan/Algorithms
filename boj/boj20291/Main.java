package boj.boj20291;

import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[] A;

    static void input(){
        N = sc.nextInt();
        A = new String[N+1];
        for(int i=1;i<N+1;i++){
            A[i] = sc.nextLine().split("\\.")[1];
        }
    }

    static void process(){
        Arrays.sort(A,1,N+1);

        for(int i=1;i<=N;){
            int cnt=1;
            int j=i+1;
            for(;j<=N;j++){
                if(A[j].compareTo(A[i])==0) cnt++;
                else break;
            }

            sb.append(A[i]).append(' ').append(cnt).append('\n');
            i=j;
        }
        System.out.println(sb.toString());
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

        double nextDouble(){
            return Double.parseDouble(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
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
}
