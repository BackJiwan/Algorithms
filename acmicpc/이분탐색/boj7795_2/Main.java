package acmicpc.이분탐색.boj7795_2;

import java.io.*;
import java.util.*;


public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

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
    }

    static int N,M;
    static int[] A,B;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N+1];
        B = new int[M+1];
        for (int i=1;i<=N;i++){
            A[i] = sc.nextInt();
        }
        for (int i=1;i<=M;i++){
            B[i] = sc.nextInt();
        }
    }

    static void pro(){
        Arrays.sort(B,1,M+1);
        int ans = 0;
        //여기에서는 A배열의 값들을 하나씩 lower_bound에 보내서 A 배열의 요소 보다 작은 값의 인덱스를 ans에 추가한다.
        for(int i=1;i<=N;i++){
            ans += lower_bound(B,1,M,A[i]);
        }
        System.out.println(ans);
    }

    static int lower_bound(int[] A,int L,int R,int X){
        // A[L~R] 까지 배열에서 X 미만의 수 중에서 제일 오른쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 L - 1 을 return 한다
        //이분탐색으로 x보다 작은수 중에서 가장 큰수의 인덱스를 고를 것이다
        int res = L-1;
        while(L<=R){
            int mid = (L + R)/2;
            //res를 갱신하는건 유효한 값일때 최신화 해야겠 지?
            if(A[mid]<X){ //만약 고른값이 찾으려는 X보다 작다면,
                res = mid;
                L = mid+1;
            }else{
                R = mid -1;
            }
        }
        return res;
    }

    public static void main(String[] args){
        int T;
        T = sc.nextInt();
        for(int i=0;i<T;i++){
            input();
            pro();
        }
    }

}
