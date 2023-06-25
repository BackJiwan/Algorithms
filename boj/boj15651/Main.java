package boj.boj15651;
/*
* 배운점 : append 사용법
* 입력 : N M (한줄)
* 출력 : 한줄에 하나씩 조건을 만족하는 수열 출력
* 내용 : 1~N까지 자연수 중복허용 M개를 고른다.
* 제한 : 1 <= (m,n) <= 7 (1초=1억,512MB)
* 구현 : M개의 자리에 1~N까지를 각각 모두 대입해본다.
* rec_func
* 만약 M개를 전부 골랐다면 => 조건에 맞는 한가지의 탐색 성공 사례
* 아직 M개를 고르지 못했다 => k번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
* 시간복잡도 : O(N^M) (7^7) = 82만
* 공간복잡도 : O(M)
* */
import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N,M;
    static int[] selected;

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

        long nextLong(){
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

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
    }

    static void rec_func(int k){
        if(k == M+1){
            //selected[1..M] 배열이 새롭게 탐색된 결과
            for(int i=1;i<M+1;i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else{
            for(int i=1;i<N+1;i++){
                selected[k] = i;
                rec_func(k+1);
            }
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(sb);
    }
}
