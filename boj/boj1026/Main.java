package boj.boj1026;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] a;
    static int[] b;
    static int[] temp;

    static void input(){
        N = sc.nextInt();
        a = new int[N+1];
        b = new int[N+1];
        temp = new int[N+1];

        for(int i=1;i<=N;i++){
            a[i] = sc.nextInt();
        }
        for(int i=1;i<=N;i++){
            b[i] = sc.nextInt();
            temp[i] = b[i];
        }
    }
    static void pro(){
        Arrays.sort(temp,1,N+1);
        Arrays.sort(a,1,N+1);
        int result=0;
        for(int i=1;i<=N;i++){
            //temp를 뒤에서부터 열면서 b의 어디 idx인지 찾는다.
            int idx = findIdx(temp[(N+1-i)]);
            //이렇게 찾아진 idx로 실제 b에서 값을 뽑은뒤 정렬된 a의 맨앞부터 곱해서 정답에 누적합
            result += b[idx]*a[i];
        }
        System.out.println(result);
    }
    static int findIdx(int target){
        for(int i=1;i<=N;i++){
            if(b[i]==target){
                return i;
            }
        }
        return 0;
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
        public FastReader(String s)throws FileNotFoundException {
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
}
