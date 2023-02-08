package boj.boj13144;

/*
* 1. sum은 기본적으로 N을 포함하므로 sum=N으로 시작
* 2. L=1부터 시작해서 R은 N+1보다 작도록 반복문
* 3. -> 메모리초과...
* */

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static long sum;
    static int[] arr;
    static boolean[] visited;

    static void input(){
        N = scan.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = scan.nextInt();
        }
    }

    static void pro(){
        int sum=N;
        for(int L=1;L<N;L++){
            visited=new boolean[100002];
            int R= L+1;
            visited[arr[L]] = true;

            while(R<=N){
                if(visited[arr[R]] == true){
                    R++;
                    break;
                }else if(visited[arr[R]]==false){
                    sum += 1;
                    R++;
                }
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args){
        input();
        pro();
    }

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
}
