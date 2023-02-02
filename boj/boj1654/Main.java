package boj.boj1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K,N;
    static int[] lines;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new int[K];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            lines[i] = Integer.parseInt(st.nextToken());
        }

        process();
        System.out.println(ans);

    }
    static boolean check(long len){ //mid를 길이로 받았을때 잘린 개수의 합이 N개 이상을 만족하는지
        long sum=0;
        for(int i=0;i<K;i++){
            if(lines[i] >= len){
                sum += lines[i]/len;
            }
        }
        return sum >= N; //N개 이상의 개수라면 T를 리턴한다.
    }

    static void process(){
        long start=0,end=Integer.MAX_VALUE;
        ans=0;
        while(start <= end){
            long mid = (start+end)/2;
            if(check(mid)){
                ans=mid;
                start = mid+1;
            } else{
                end = mid -1;
            }
        }

    }

}
