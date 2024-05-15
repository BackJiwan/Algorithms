package boj.boj1806;
/*
* 1.정답의 최대치(int=-21억 ~ 21억)
* - 정답의 최대치는 합을 S를 넘기기 위해 길이 N이 모두 사용되어야 하는 경우이므로 최대 10만 => `int사용`
* 2.복잡도 계산(1초 = 1억번 연산)
* - 투 포인터를 이용하면 왼쪽 포인터를 결정하는 O(N), + 오른쪽 포인터가 이동하는 O(N)이 별개이므로 O(N) => 10만
* 3.문제이해
* - 10만 이하의 자연수, 길이 N 수열, 부분합중 S를 넘기는 가장 짧은 길이
* - 투 포인터를 사용 O(N=10만)
* 4.수도 코드
* - 입력()
*   N,S 입력받기
*   반복문=N번의 수열 입력받기
* - 프로세스()/[투포인터로 정답을 갱신하며 최소 S를 가지고 나오기
*
*
* */

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
        int R = 0, sum =0, ans = N+1;
        //L은 1부터 N 까지 이동하는 포인터
        //L-1의 값을 구간합 (sum)에서 제거한다.
        for(int L=1;L<=N;L++){
            //L-1을 구간합에서 빼기
            sum -= arr[L-1];
            // R을 옮길수 있는 위치까지 보내기
            while(R+1<=N && sum<S){ //sum이 M보다 작은 동안에만 반복한다. 커지는 순간 탈출
                R++;
                sum += arr[R];
            }
            // L~R 까지의 합이 조건을 만족하면 정답 갱신
            if(sum >= S){
                ans = Math.min(ans,R-L+1);
            }

        }
        //ans의 값이 한번도 갱신 안됐다면 초기값으로 셋팅
        if(ans==N+1){
            ans = 0;
        }
        System.out.println(ans);
    }

    public static void main(String[] args){
        input();
        pro();
    }
}
