package boj.boj9095;
/*
* boj9095
* Dynamic Programming
* 1.정답의 최대치:
* 2.복잡도 계산:
* 3.문제 이해:
* - 정수 n이 주어지면 1,2,3의 합으로 나타내는 방법의 수  구하기
* - 테스트케이스로 주어짐, n은 11보다 작다.
* 4.풀이 구상:
* - D[1]=1: 1
* - D[2]=2: 1+1 / 2
* - D[3]=4: 1+1+1 / 2+1/
*           1+ 2/
*           3
* - D[4]=7: 1+1+1+1 / 2+ 1+1/ 1+2+1/ 3+1
*           1+1+2/ 2+ 2
*           1+3/
* - D[5]=13: 이 부분은 기존 D[4]+1(=5) 하면됨
*            이 부분은 기존 D[3]+2(=5) 하면됨
*            이 부분은 기존 D[2]+3(=5) 하면됨
* 즉 이 이후로(4<=D)는 직전수에 1을 더하거나 2앞선수의 경우에 2를 더하거나 하면 되기 때문애
* 점화식 D[i] = D[i-1]+D[i-2]+D[i-3] 이다.
 * */

import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int C;
    static int N;
    static int[] D;

    static void input(){
        N = sc.nextInt();
        sb.append(D[N]).append('\n');
    }

    static void process(){
        D = new int[11];

        D[1] = 1;
        D[2] = 2;
        D[3] = 4;

        for(int i=4;i<11;i++){
            D[i] = D[i-1] + D[i-2] + D[i-3];
        }
    }

    public static void main(String[] args){
        C = sc.nextInt();
        process();
        while(C>0){
            input();
            C--;
        }
        System.out.println(sb.toString());
    }
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
        long nextLong(){
            return Long.parseLong(next());
        }
    }
}
