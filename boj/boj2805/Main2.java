package boj.boj2805;

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
        long nextLong(){
            return Long.parseLong(next());
        }
        String nextLine(){
            String str ="";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
    static int N,M;
    static int[] arr;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextInt();
        }
    }
    static boolean determination(int h){
        //자르려는 높이 h를 받았을 때에 잘라보고 그 합이 M보다 같거나 크면 T리턴
        long sum=0;
        for(int i=1;i<=N;i++){
            if(arr[i]>h){
                sum += arr[i]-h;
            }
        }
        return sum>=M;
    }
    static void pro(){
        long L=0,R=2000000000;
        int ans=0;
        while(L<=R){ //만약 된다면 L-> R = YYYYYYYYYY(Y*)NNNNNNNNNN 이런형태일것이고, Y*을 찾아야 한다.
            //그렇다면 유의미한 구간은 '같거나 작을때' 이고 이때 ans를 mid로 갱신하고 더이상 왼쪽은 안봐도 되니 L = mid+1로 땡긴다.
            int mid = (int)((L+R)/2);
            if(determination(mid)){ //이구간은, 정답인데 더 큰수도 되는지 체크해봐야 하므로 왼쪽 버려야함, ans 갱신하고
                ans = mid;
                L = mid+1;

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
