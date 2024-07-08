package acmicpc.완전탐색.boj15649;

import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int arr[];
    static boolean check[];


    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M+1];
        check = new boolean[N+1];
    }

    static void rec_func(int idx){
        //오름차순,내림차순 등은 필요 없기 때문에 prev를 전달할 필요 없고
        //현재까지 사용 여부만 check에 true로 체크해주면 다시 안쓰이니까 괜춘

        //현재 값을 넣어야 하는 idx 위치를 매개변수로 전달받았다.
        //1~M 까지의 idx가 가능하다.

        //종료조건 idx > M sb에 있는 내용을 append하고 return해서 재귀 종료
        if(idx>M){
            for(int i=1;i<=M;i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        //그게 아니라면 for 통해서 1~N 까지 고르는데
        //만약 check[]에서 사용된적있으면 continue래서 반복조건 돌리고
        //아닌 경우에만 arr[idx]에 현재 num 넣어주고
        //idx+1 해서 재귀 호출
        //호출 끝나면 다시 0으로 돌려주기

        for(int num=1;num<=N;num++){
            if(check[num]) continue;

            arr[idx] = num;
            check[num] = true;
            rec_func(idx+1);
            check[num] = false;
            arr[idx] = 0;
        }

    }

    static void process(){
        rec_func(1);
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
    }
}
