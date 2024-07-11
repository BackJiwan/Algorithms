package acmicpc.두포인터;

import java.io.*;
import java.util.*;
public class boj2470 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static int N;
    static int[] arr;
    static int ans;

    static void input(){
        N = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextInt();
        }
    }

    static void process(){
        //투포인터를 위해 정렬하기
        Arrays.sort(arr,1,N+1);

        //L,R,sum 선언
        //v1,v2 선언
        int v1=0,v2=0;
        int L = 1;
        int R = N;
        int sum;
        ans = Integer.MAX_VALUE;

        //L<R 인동안 반복
        while(L<R){
            sum = arr[L] + arr[R];
            //만약 sum의 절대값이 ans보다 작으면 v1,v2를 갱신
            if(ans>=Math.abs(sum)){
                v1 = L;
                v2 = R;
                ans = Math.abs(sum);
            }
            //만약 sum이 음수이면,L++
            //양수이면 R++
            if(sum<0){
                L++;
            }else{
                R--;
            }

        }
        sb.append(arr[v1]).append(' ').append(arr[v2]);
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


//    static void process(){
//        //투포인터로 두용액을 고르려면
//
//        //정렬하기
//        Arrays.sort(arr,1,N+1);
//
//        //제일 왼쪽부터 L을 잡고 오른쪽 부터 R을 잡음
//        // -99 -2 -1 4 98
//        //일단 L과 R을 더해봄
//        //R을 하나 줄여보고 갱신돼? 그럼 반복
//        //R을 하나 줄였는데 갱신안돼? 그럼 L을 땡기고 반복
//        //반복조건은 L<=R while
//
//        int L=1;
//        int R=N;
//        int v1=0,v2=0;
//        int sum;
//        ans = Integer.MAX_VALUE;
//        while(L<R){ //**중요 같으면 안됨
//            sum = arr[L]+arr[R];
//
//            if(ans>=Math.abs(sum)) {
//                v1 = L;
//                v2 = R;
//                ans = Math.abs(sum);
//            }
//            if(sum>0) { //sum이 양수면 오른쪽을 줄여야함
//                R--;
//            }else{
//                L++;
//            }
//
//        }
//        sb.append(arr[v1]).append(' ').append(arr[v2]);
//        System.out.println(sb.toString());
//    }