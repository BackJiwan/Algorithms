package boj.boj11652;
/* feat: solve boj11652 [S4]카드
* 1.정답의 최대치
* - 기본적으로 2^62는 int를 넘기 때문에 2^63-1 까지 가능한 long을 사용
* 2.복잡도 계산(1초:1억번)
* - 정렬 : NlogN, 반복문 1회 : N
* - N = 카드의 개수 10만
* 3.문제이해
* - 숫자카드 N장 보유, 수의 범위 = 2^(-62) ~ 2^62
* - 준규가 보유한 숫자카드 중에서 가장 많이 가지고 있는 정수를 구하기,
* - 만약 가장 많은 정수가 여러개라면 작은것을 출력하기
* 4.구상A
* - int(4바이트=32비트=2^31-1까지) , long(8바이트=64비트=2^63-1까지 가능) 이므로 long 사용함
* - "가장 많이 가지고 있다" "최빈값" => 정렬 이용해서 카운트
* - "여러개라면 작은것을 출력" => 한번 정렬하고 value가 몇번나왔는지를 check[value] ++ 를 통해서 각 인덱스는
* - 밸류를 의미하고 거기에는 등장한 카운트가 있다.
* - 나중에 정답을 꺼낼때에 check를 근데 이거 숫자 크기가 너무 크니까 숫자값을 인덱스로 가지는 배열은 사용하면 안됨
* 5.구상B
* - "가장 많이 가지고 있다","최빈값" => 정렬하고 반복순회하며 prev와 숫자가 바뀐 시점부터 카운트
* - long 배열을 그냥 정렬한다(NlogN) , 배열의 크기만큼 1회 반복 순회한다.
* - arr[i=1~N-1] i가 i-1과 값이 다르면 현재 static cnt가 max보다 크면(같거나를 뺏으므로 같은 경우에는 작은거만 max에 남는다)
*   max = cnt로 초기화 하고 현재 i를 result_idx에 대입 한다.
*   (값이 다르다:새롭게 등장한수,)
*   값이 다르지 않은 else의 경우 직전값과 현재값이 같으므로 cnt를 증가시킨다.
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
        public FastReader(String s)throws IOException{
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
    static int N; //카드의 개수는 10만개라서 int 가능
    static long[] arr;
    static long max;
    static int result_idx;
    static void input(){
        N = sc.nextInt();
        arr = new long[N+1];
        max = Long.MIN_VALUE;
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextLong();
        }
    }
    static void pro(){
        Arrays.sort(arr,1,N+1); //2번째 제출에서도 내가 1부터 만든 배열임을 까먹고 정렬을 잘 못한듯
        int cnt =1; //직전제출에서는 0 초기화였으나 논리적으로 1로 바꿈
        for(int i=2;i<=N;i++){
            if(arr[i]!=arr[i-1]){
                if(cnt>max){
                    max = cnt;
                    result_idx = i-1;
                }
                cnt = 1; // 3번째 제출에서 cnt의 위치가 if문 안에 있어서 문제였던듯
            }else{
                cnt++;
            }
        }
        //배열의 마지막 요소를 카운트하고 정답인경우 숫자가 바뀌지 않기 때문에 max를 갱신하는 작업을 하지 못한다
        //따라서 예외 케이스인 마지막 요소까지 포함한 cnt를 반복문 바깥에 추가해준다.
        if(cnt>max){
            max = cnt;
            result_idx = N;
        }
        System.out.println(arr[result_idx]);
    }

    public static void main(String[] args){
        input();
        pro();
    }
}
