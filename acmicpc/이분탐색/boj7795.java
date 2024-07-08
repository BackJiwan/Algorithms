package acmicpc.이분탐색;
/*
* A의 크기배열, B의 크기배열이 주어진다.
* A의 크기가 B보다 큰 쌍이 몇개나 있는지 구해보자
* 이분탐색
* */
import java.io.*;
import java.util.*;
public class boj7795 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int[] A;
    static int[] B;
    static int ans;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N+1];
        B = new int[M+1];
        for(int i=1;i<=N;i++){
            A[i] = sc.nextInt();
        }
        for(int j=1;j<=M;j++){
            B[j] = sc.nextInt();
        }
    }

    static void process(){
        //일단 A중에 B보다 큰 경우의 수를 찾으면 되니까 개수만 찾는거라서 둘다 정렬해서 기존 인덱스를 잃어도 상관 없다.
        //일단 A랑 B를 정렬

        //반복문을 통해서 A의 인덱스를 1부터 N 까지 증가 시키겠다.
        //그러다가 B의 cursor(b의 인덱스 i~까지)보다 큰 경우가 왔다면 N-i 만큼 ans에 업데이트
        //업데이트 했으면 B의 다음 인덱스 값의 cursor에 해당 하는 값을 찾기 위해서 A의 인덱스를 땡긴다.
        //그런데 이 작업을 위해서 이분탐색으로 A의 하나를 잡은 것을 B의 값 중에서 target보다 작은 값중에 가장큰 인덱스를 리턴하면 된다.

        //즉 반복문으로 A의 i~N 까지 하나씩 잡고 B를 이분탐색하다가 L>R 까지 while 돌면서 만약 B[mid] 값이

        Arrays.sort(A,1,N+1);
        Arrays.sort(B,1,M+1);

        ans = 0;

        for(int i=1;i<=N;i++){
            int target = A[i];
            int L = 1;
            int R = M;
            int idx=0;
            while(L<=R){ //이분탐색은 무조건 L=R 인 부분에서도 한번의 계산은 일어나야 함 !!
                int mid = (R+L)/2;
                if(B[mid]<target){
                    //위에껀 헷갈렸었음
                    //다시하면 내가 들고온 target은 A의 요소, B를 먹으려면 A의 요소보다 작은애들의 개수를 가지고 가야해
                    //그러면 target보다 작은애들이 유의미한거고
                    //작은거중 큰게 찾고 싶어 -> 작은 부분이 유의미하다 -> 이미 작은거 확정난 부분은 굳이 안봐도돼 -> 오른쪽을 살려서 오른쪽중 혹시 더 작은애가 있는지 검사화러가자
                    //그러니까 일단 지금 찾은 mid는 확실히 정답일수는 있으니 아쉽지만 idx에 갱신해주기
                    idx = mid;
                    L = mid +1;
                }else{ //만약 찾으려는 값보다 내용물이 같거나 크면 아무 의미 없으니까 오른쪽을 모두 버리고 왼쪽 중에 찾으러 떠난다. 정답 갱신할 일도 없다.
                    //오른쪽의 첫번쨰 요소라고 하더라도 target보다 작거나 같을 수 있기 때문이다.
                    R = mid-1;
                }
            }
            //이분 탐색을 하고 나왔다면 idx에는 찾으려는 값보다 큰 값중에 가장 작은 인덱스 또는, 못찾았으면 0이 있겠죠
            ans += idx;
        }

        sb.append(ans).append('\n');
    }

    public static void main(String[] args){
        int Q = sc.nextInt();
        for(int i=0;i<Q;i++){
            input();
            process();
        }
        System.out.println(sb.toString());
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
