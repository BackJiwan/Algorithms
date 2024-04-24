package boj.boj11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int result;

    static void input() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N+1];
            result = 0;

            String str =br.readLine();
            for(int i=0;i<N;i++){
                int temp = (str.charAt(i))-'0';
                arr[i] = temp;
                result += temp;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        input();
        System.out.println(result);
    }
}
