package boj.boj15652;

/*
* 비내림차순 : 뒤에 오는 수는 앞에 오는수보다 같거나 커야 한다
* 시간복잡도 : M개의 자리에 각자리마다 최소한 N번중 고를수 있다 = N^M = 8^8 =
* */

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

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
            while(st==null||!st.hasMoreElements()){
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
            try {
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
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[M+1];
    }

    static void rec_func(int k,int start){
        if(k==M){
            for (int i=0;i<M;i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append("\n");
            return;
        }
        for(int i = start;i<=N;i++){
            arr[k] = i;
            rec_func(k+1,i);
        }
    }

    public static void main(String[] args){
        input();
        rec_func(0,1);
        System.out.println(sb);
    }
}
