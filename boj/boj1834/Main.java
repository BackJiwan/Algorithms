package boj.boj1834;
//2백만 이하의 자연수 N
//시간제한 2초 = 2억번

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long ans=0;


        // y = ax + b 인데 a가 b인 경우의 y를 누적해야함
        // a==b 를적용하면 y = ax + a = a(x+1) 인데 여기서 x가 n 이니까
        // ans += i(n+1)

        //
        for(long i=1;i<N;i++){
            ans += (i*(N+1));
        }

        System.out.println(ans);
    }
}
