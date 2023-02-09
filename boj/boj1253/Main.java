package boj.boj1253;
/*
* 1. N<2000,A(i)<10억,ans<2000 이므로 정답도 int로 가능
* 2. 입력 N 받기(int)
* 3. N번 반복하여 int[] arr에 수열 입력을 저장
* 4. 타겟을 1~N까지 이동(O(N))
*    하나의 타겟을 선정하였을때에 L=1,R=N의 범위에서 합으로 나타낼수 있는지 여부를 bool 값으로 반환하는 func
* 5. func는 bool을 반환한다. 매개변수는 타겟의 인덱스 = O(N)
*    지역변수 L=1,R=N으로 while 반복문을 돌면서 L==R이 되면 종료된다.
*    L,R이 타겟 인덱스와 동일한 경우 건너뛴다.
*    두 수의 합이 타겟수보다 크면 R을 줄이고
*    두 수의 합이 타겟수보다 작으면 L을 늘린다.
* 6. 그전에 일단 Array.sort(arr,1,N+1) 배열을 정렬한다. = O(NLogN)
* */
import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int ans=0;
    static int[] arr;

    public static void main(String[] args){
        input();
        pro();
    }

    static void input(){
        N = scan.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = scan.nextInt();
        }
    }

    static void pro(){
        Arrays.sort(arr,1,N+1);
        for(int idx=1;idx<=N;idx++){
            if(func(idx)){
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean func(int target_idx){
        int L=1,R=N;
        int sum=0;
        while(L<R){
            if(L==target_idx) L++;
            else if(R==target_idx) R--;
            else{
                sum = arr[L]+arr[R];
                if (sum>arr[target_idx]) {
                    R--;
                }else if(sum == arr[target_idx]){
                    return true;
                } else{
                    L++;
                }
            }
        }
        return false;
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
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
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
}
