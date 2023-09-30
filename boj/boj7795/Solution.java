package boj.boj7795;

/*
* 1. 정답의 최대치 : A=2만,B=2만, 모든 A는 모든 B보다 크다. => 4억 => int
* 2. 복잡도 계산 : A에서 하나를 잡고 B를 순회하면 몇개나 작은지 계산
* -> N * M => 4억번의 연산 => 4초 => 시간 초과!
* 2-1. 복잡도 계산 : B를 정렬한다 : M logM
* + A를 하나 잡고 B에서 이분탐색으로 최초로 작은 변수 인덱스를 찾는다 ( 그 아래는 자동적용이므로) : N logM
* -> (N+M) log M :
* 3. 구현
* - testcase 입력은 main 에서 받아준다 -> 2차원 배열보다 낫다
* - N과 M 입력받고 int 배열에 담아준다.
* - B 배열을 오름차순 정렬해준다. (단순배열이므로 Array.sort(arr) 사용)
* - A에서 앞에서 부터 하나씩 잡아서 정렬된 B내부에서 이분탐색하다가 나보다 작은 수중 가장 큰수의 인덱스를 리턴하도록한다.
*   매개변수로 int[] arr, int L , int R, int X 을 받는다.
*   최초 호출시에는 B 배열 전체와 1, arr.length 를 넘겨준다
*   int mid = L+R/2 를 통해서 중간값을 잡아주고
*   if mid == X라면 바로 mid return 해버리기
*   elif mid<X 라면 L 대신 mid+1을 넘겨서 오른쪽 부분을 다시 이분 탐색하도록한다.
*   elif mid>X 라면 R대신 mid-1을 넘겨서 왼쪽 부분을 다시 이분 탐색하도록 한다.
*   if(L>=R) 인경우라면 같은것도 없고 더이상 탐색도 안된 시점이므로 바로 mid Return 해버린다.
* */

import java.util.*;
import java.io.*;

public class Solution {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        StringTokenizer st;
        BufferedReader br;

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

        Double nextDouble(){
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

    static int N;
    static int a,b;
    static int[] arr,brr;

    static void input(){
        a = scan.nextInt();
        b = scan.nextInt();
        arr = new int[a+1];
        brr = new int[b+1];
        for(int i=1;i<=a;i++){
            arr[i] = scan.nextInt();
        }
        for(int i=1;i<=b;i++){
            brr[i] = scan.nextInt();
        }
    }

    static int search(int[] arr,int L,int R,int X){
        int mid = (L+R)/2;
        if(L>R){
            return mid;
        }

        if(brr[mid]>=X){
            return search(arr,L,mid-1,X);
        }else{
            return search(arr,mid+1,R,X);
        }
    }

    static void pro(){
        int ans=0;
        Arrays.sort(brr);
        for(int i=1;i<=a;i++){
            ans += search(brr,1,b,arr[i]);
        }
        sb.append(ans).append('\n');
    }

    public static void main(String[] args){
        N = scan.nextInt();
        for(int i=0;i<N;i++){
            input();
            pro();
        }
        System.out.println(sb);
    }
}
