package boj.boj7795;
/*
* 1.정답의 최대치
* 2.복잡도 계산
* - 정렬 log(N) 이분탐색 log(N)
* 3.문제 이해하기
* - A만 B를 먹을 수 있다. A가 먹을수 있는 B는 A의 크기보다 작은 먹이만 먹을 수 있다.
* - A배열, B배열이 있을 때에 A의 각 요소들이 B에서 몇개나 먹을 수 있는지를 합산해서 먹을수 있는 쌍의 개수구하기(sum)
* 4.구상 A
* - 크기가 중요하다, A와 B를 각각 정렬하고 A를 반복문으로 앞에서부터 하나씩 꺼낸다
* - 꺼낸 A의 값을 X 매개변수로 lower_bound에 전달한다.
* - lower_bound는 이분탐색함수로 L,R범위로 탐색대상배열 A에서 X보다 작은수중 가장큰수의 인덱스를 리턴한다.
* - if(L>=R) => L이 R보다 같거나 크다라는건 X가 존재하지 않으며 어쨋든 작은수를 찾는게 목표이니 L-1을 리턴한다.
*   추후 (L-1)+1 과는 크로스 체크 해주면 된다.
* - 이분 탐색을 위해서 L+R/2 = mid로 mid를 설정하고
*   if A[mid]<X => L = mid+1로 떙겨서 lower_bound 호출
*   else if A[mid]>X => R = mid-1로 땡겨서 lower_bound 호출
*   else => A[mid]의 값이 X이므로 return mid
*
* */
import java.io.*;
import java.util.*;
public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws FileNotFoundException{
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

    static int N,M;
    static int[] A,B;

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
    //중요한 것에 집중해서 조건 세우기
    //궁금한 것은 X보다 "작은수"중 가장 큰 인덱스이다. 작은수만 필터링해서 이분하면 된다.
    static int lower_bound(int[] A,int L,int R,int X){
        //A[L~R] 에서 X 미만의 수(X보다 작은 수) 중 제일 오른쪽 인덱스를 return 하는 함수
        //그런게 없다면 L-1을 return 한다.

        int res = L-1;
        while(L<=R){
            int mid = (L+R)/2;
            if(A[mid]<X){ //일단 mid의 값이 X보다 작은건 확인되었으므로 res를 갱신해도 된다.
                res = mid;
                L = mid+1;
            }else{ //else의 경우는 mid의 값이 X보다 같거나/크기 때문에 갱신 위험성이 있어서 갱신하지 않는다.
                R = mid-1;
            }
        }
        return res;
    }
    static void pro(){
        // B배열에 대해 이분탐색을 위해 정렬
        Arrays.sort(B,1,M+1);

        int ans = 0;
        for(int i=1;i<=N;i++){
            //A[i]를 선택했을 때, B에서는 A[i] 보다 작은게 몇개나 있는지 count 하기
            ans += lower_bound(B,1,M,A[i]);
        }
        System.out.println(ans);
    }

    public static void main(String[] args){
        int TT;
        TT = sc.nextInt();
        for(int tt=1;tt<=TT;tt++){
            input();
            pro();
        }

    }
}
