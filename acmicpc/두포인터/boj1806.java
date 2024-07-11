package acmicpc.두포인터;
/*
* 자연수 최대값 10,000
* 길이 최대값 : 100,000 이다.
* S는 100,000,000 => 10^8\
* 연속된 수들의 부분합 중에, 그 합이 S 이상이 되는 것 중 가장 짧은것
* start,end 포인터를 잡고 start=1 end=start+1 부터 시작해서
* */
import java.io.*;
import java.util.*;
public class boj1806 {
    static FastReader sc = new FastReader();
    static StringBuilder sb =  new StringBuilder();

    static int N,S;
    static int[] A;

    static void process(){
       int R=0,sum=0,ans=N+1;
       for(int L=1;L<=N;L++){
           //L을 땡겼기 때문에 sum에서 L을 감소 시켜줘야 함
           sum -= A[L-1];
           //while문을 통해서 sum보다 S가 커지는 순간이거나 R이 끝에 닿은 순간 까지 반복
           while(R+1<=N && sum < S){
               sum += A[++R];
           }
           //탈출했으면 sum이 S를 넘은순간 or R이 밖으로 넘어간 순간

           if(sum > S){
               ans = Math.min(ans,R-L+1);
           }

       }

       if(ans == N+1){
           ans = 0;
       }
       System.out.println(ans);

    }

    static void input(){
        N = sc.nextInt();
        S = sc.nextInt();
        A = new int[N+1];
        for(int i=1;i<=N;i++){
            A[i] = sc.nextInt();
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
}

//    static void process(){
//        int R = 0, sum = 0, ans = N+1;
//        for(int L=1;L<=N;L++){
//            // L이 한칸 땡겨졌다는 가정이므로 sum에서 L-1 값을 빼주기
//            sum -= A[L-1];
//
//            //R을 가능한 오른쪽으로 땡기기(범위내부,sum이하)
//            while(R+1<N && sum <S){
//                //R+1이 통과된거니까 R+1을 덧셈에 활용할 것임
//                //R의 초기값은 0이기 때문에 R+1인 1부터 덧셈을 시작한다는 의미
//                sum += A[++R];
//            }
//
//            // 여기로 나왔다는 것은 R이 범위 밖으로 나갔거나, sum이 S를 최초 한번 넘겼다는 의미
//            //ans를 갱신(ans는 길이임 최단길이를 그때그때 판별)
//            if(sum>S){
//                ans = Math.min(ans,R-L+1);
//            }
//        }
//        //그럼에도 ans가 아직도 초기값이라면 한번도 조건에 맞는 sum을 몾찾은거니까 0 출력
//        if(ans == N+1){
//            ans = 0;
//        }
//        System.out.println(ans);
//    }


//    static void process(){
//        int R = 0, sum = 0, ans = N+1;
//        for(int L=1;L<=N;L++){
//            sum -= A[L-1];
//
//            while(R+1<N && sum < S) {
//                sum += A[++R];
//            }
//
//            if(sum > S){
//                ans = Math.min(ans,R-L+1);
//            }
//        }
//        if(ans == N+1){
//            ans = 0;
//        }
//        System.out.println(ans);
//    }
