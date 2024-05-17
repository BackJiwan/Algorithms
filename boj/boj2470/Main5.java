package boj.boj2470;
//투 포인터 사용

import java.io.*;
import java.util.*;
public class Main5 {
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

        Long nextLong(){
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
    public static void main(String[] args){
         input();
         pro();
    }

    static int N;
    static int[] arr;

    static void input(){
        //입력 값 N, arr[] 입력받기
        N = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextInt();
        }
    }

    static void pro(){
        //정렬 먼저 NlogN  1~ N 인덱스 까지 정렬
        Arrays.sort(arr,1,N+1);

        int v1=0,v2=0; // 정답에 사용할 변수
        int best_sum=Integer.MAX_VALUE;
        int L=1,R=N;
        //투 포인터 양쪽에서 조이면서 v1,v2 갱신하기
        // L과 R이 번갈아가면서 줄어들 수 있기 때문에 for로 L만 땡기는게 아니라 while 조건 반복이 필요
        while(L<R){
            int sum = arr[L]+arr[R];
            //Math.min(Math.abs(sum),best_sum); //혹시 갱신 가능성이 있
            if(Math.abs(sum)<=best_sum){
                v1 = arr[L];
                v2 = arr[R];
                best_sum = Math.abs(sum);
            }
            if(sum<0){ //가장큰 음수와 가장큰 양수를 더했는데 음수 이므로 음수를 줄여야함
                L++;
            }else{
                R--;
            }
        }
        //ans에 사용되는 v1,v2를 갱신
        //L+R 이 음수 => 음수+양수 = 음수 이므로 더 작은 양수를 더할 의미가 없다. = L을 땡김
        sb.append(v1).append(' ').append(v2).append('\n');
        System.out.print(sb.toString());
    }
}
