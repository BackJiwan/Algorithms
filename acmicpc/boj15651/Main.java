package acmicpc.boj15651;

//완전탐색
import java.io.*;
import java.util.*;

public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int[] arr;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N+2];
    }
    static void process(){
        rec_func(1);
        System.out.println(sb.toString());
    }

    static void rec_func(int idx){
        //직전에 고른 idx를 전달받았다.

        //종료조건 idx>N인 경우 1~N 까지 모두 채웠기 때문에 현재 arr를 sb에 append 하고 return
        if(idx>M){
            for(int i=1;i<=M;i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        //종료조건이 아니라면 for 문을 통해서 arr의 현재 idx 자리에 들어갈 값을 골라서 넣고 다음 idx를 재귀호출
        for(int i=1;i<=N;i++){
            arr[idx] = i;
            rec_func(idx+1);
            arr[idx] = 0; //0으로 돌리기
        }

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
    }
}
