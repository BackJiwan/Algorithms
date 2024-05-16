package boj.boj1806;

import java.io.*;
import java.util.*;
public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N,S;
    static int[] arr;
    static void input(){
        N = sc.nextInt();
        S = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextInt();
        }
    }

    static void pro(){
        //ans의 초기값은 격전지 바깥에 일단 둔다.
        int ans=N+1,R=0,sum=0;
        // L을 하나 잡고
        for(int L=1;L<=N;L++){
            //L을 한번 땡겼으니까 기존의 sum에서 L-1 값을 빼준다.
            sum -= arr[L-1]; //초기에는 0-0 이니까 상관X
            // R을 가능한 땡긴다.
            while(R+1<=N && sum < S){
                R++;
                sum += arr[R];
            }
            //위의 whlie 문을 탈출했다면 R이 N보다 커졌다 (끝 도착)
            //또는 sum이 S 보다 같거나 커진 순간의 R과 함께인 경우이다.
            if(sum >= S)
                ans = Math.min(ans,R-L+1);
        }


        //정답을 갱신한다.
        if(ans >= N+1){
            ans = 0;
        }
        System.out.println(ans);
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

}
