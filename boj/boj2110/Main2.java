package boj.boj2110;
/*
* 1.정답의 최대치
* 2.복잡도 계산
* - N개의 집을 정렬하는데에 NlogN이 소요 (20만*log20만
* 3.문제 이해
* - 도현이의 집 N개가 수직선 위 , 좌표 x1~xn
* - C개의 공유기를 나눠서 설치
* - 인접 공유기 사이의 거리를 최대로
* - C개의 공유기를 N개의 집에 **두 공유기 사이의 거리를 최대**로 설치
* - 집의 개수 : 2~20만, 공유기의 개수 : 2~집의 개수,
*   집의 좌표 : 0~10억
* 4.구상 A
* - 최대값 : 매개변수탐색(이분탐색)으로 변경의 여지가 있따.
* - 기존문제 : C개의 공유기를 설치했을 때에 최대 인접거리는 얼마인가?
* - 변경문제 : 인접 거리 얼마까지 설정해도 C개의 공유기를 설치할 수 있는가? YYYYYYYYYYY(정답)NNNNNNNN
* - 결정문제 : 인접거리 d가 주어지면 정렬된 배열에서 '그리디'로 몇개의 공유기를 설치할 수 있는지 탐색한다.
*   ex) 1,2,4,8,9
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
        String nextLine(){
            String str= "";
            try{
                str= br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }

    static int N,C;
    static int[] arr;

    static void input(){
        N = sc.nextInt();
        C = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextInt();
        }
    }

    static boolean determination(int d){
        //거리 d를 인접 공유기 사이의 거리 제한으로 했을때 집마다 조건에 맞을때에만 설치한다면 설치되는 공유기가 C개 이상인가?
        //예 ) 1 2 4 8 9
        //d=1로 들어옴
        //먼저 첫번째 집, 1은 설치하고 반복문을 들어간다.
        int cnt = 1, last = arr[1];

        for(int i=2;i<=N;i++){
            if((arr[i]-last)<d){
                continue;
            }
            cnt++;
            last = arr[i];
        }
        return cnt >= C;
    }

    static void pro(){
        Arrays.sort(arr,1,N+1);
        int L=1, R=1000000000, ans=1;
        while(L<=R){
            int mid = (L+R)/2;
            if(determination(mid)){ //YYYYYYYYYYYY(*정답)NNNNNNNN)
                //공유기 사이의 mid 거리 제한도 C개의 공유기를 놓을수 있다.
                //정답을 갱신하고 가능한 더 큰 값을 찾으러 오른쪽을 살린다. (왼쪽을 mid+1로 갱신)
                ans = mid;
                L = mid + 1;
            }else{
                R = mid -1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args){
        input();
        pro();
    }
}
