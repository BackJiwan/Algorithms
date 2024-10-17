package acmicpc.정렬.boj1015_2;
/*
* 포인트 : 입력된 배열A의 원소,idx를 기록한다.
* 이 배열 A를 비내림차순으로 정렬시킬수 있는 수열 P[i]를 구해야하는데
*
*
* */
import java.io.*;
import java.util.*;

public class Main {
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

        double nextDouble(){
            return Double.parseDouble(next());
        }
    }

    static int N;
    static int[] P;
    static Elem[] B;

    static class Elem implements Comparable<Elem>{
        int num;
        int idx;

        @Override
        public int compareTo(Elem other){ //오름차순으로 정렬됨
            return num - other.num;
        }
    }

    static void input(){
        N = sc.nextInt();
        B = new Elem[N];
        P = new int[N];
        for(int i = 0;i<N;i++){
            B[i] = new Elem();
            B[i].num = sc.nextInt();
            B[i].idx = i;
        }
    }

    static void pro(){
        Arrays.sort(B);
        for(int i=0;i<N;i++){
            P[B[i].idx] = i; //B[i]가 가야할 곳은 P에 i로 기록해야하고, 그 가야할 곳의 좌표는 기존의 idx 값이다.
            //이미 B는 정렬이 되어 있기 때문에 0부터 i를 열어보면서 최종배열의 i위치로 가라고 말해주면 된다.
        }
        for(int i=0;i<N;i++){
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        input();
        pro();
    }

}
