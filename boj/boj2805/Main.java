package boj.boj2805;

import java.util.*;
import java.io.*;

public class Main {
    static VersatileReader sc = new VersatileReader();
    static StringBuilder sb = new StringBuilder();

    static class VersatileReader{
        BufferedReader br;
        StringTokenizer st;
        public VersatileReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public VersatileReader(String s) throws FileNotFoundException{
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

    static int N,M;
    static int[] tree;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        tree = new int[N+1];
        for(int i=1;i<=N;i++){
            tree[i] = sc.nextInt();
        }
    }

    static boolean determination(int H){
        //H 높이로 나무들을 잘랐을 때에 M만큼을 얻을 수 있으면 True, 없으면 False 를 리턴하는 함수
        long sum =0;
        for(int i=1;i<=N;i++){
            if(tree[i]>H){
                sum += tree[i]-H;
            }
        }
        return sum >=M;
    }

    static void process(){
        long L=0,R=2000000000,ans =0;
        //[L~R] 중에 정답이 되는 하나의 ans 가 존재한다.
        //이분 탐색과 determination 문제를 이용해서 ans를 빠르게 탐색
        while(L<=R){
            int mid = (int)((L+R)/2);
            if(determination(mid)){ //현재까지는 왼쪽이 전부 YES 라는 의미, 왼쪽및 본인 버림
                ans = mid; //본인 값 임시저장
                L = mid+1;
            }
            else{
                R = mid -1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] arsg){
        input();
        process();
    }
}
