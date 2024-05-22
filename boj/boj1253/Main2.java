package boj.boj1253;
/*
* 1.정답의 최대치(int:-21억~21억)
* - 1~10억 중 숫자 2000개로 이루어져 있다.
* - 정답의 수의 개수 이므로 2000개가 최대이고,두수의 합 이므로 최대 20억이라
* = int로 가능하다.
* 2.복잡도 계산(1초: 1억번)
* 3.문제 이해
* - N개의 수가 주어진다.
* - 어떤 수가 다른 수 두개의 합으로 나타낼 수 있는 경우의 수를 구하기
* 4.풀이 구상
* - 정렬을 한다. (NlogN)
* - 맨 앞에서부터 하나씩 골라서 (N)
* - 투포인터로 해당 수를 만들 수 있는 경우를 탐색한다. (N)
*       - func는 target_idx를 전달받아서 로컬 target을 설정하고
*       - while(L<R) 인동안 반복하며
*       - L이나 R이 target인 경우 넘어가고
*       - 그 외에는 L+R - target의 값을 비교하며 양수일 때 음수일떄 나누어서 L,R을 땡긴다.
* */
import java.io.*;
import java.util.*;
public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static int N;
    static int[] A;

    static void input(){
        N = sc.nextInt();
        A = new int[N+1];
        for(int i=1;i<=N;i++){
            A[i] = sc.nextInt();
        }
    }

    static boolean func(int target_idx){
        int L=1, R=N;
        int target = A[target_idx];

        while(L<R){
            if(L==target_idx){
                L++;
            }else if(R==target_idx){
                R--;
            }else{
                if(A[R]+A[L]>target){ //양수가 더 큰 경우 이므로 양수를 줄이기
                    R--;
                }else if (A[R]+A[L]==target){
                    return true;
                }else{
                    L++;
                }
            }
        }

        return false;
    }

    static void pro(){
        int ans = 0;
        //정렬
        Arrays.sort(A,1,N+1);

        //1~N까지의 수를 하나씩 func에 넣어서
        //만약 참인 경우만 ans ++
        for(int i=1;i<=N;i++){
            if(func(i)){
                ans++;
            }
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
