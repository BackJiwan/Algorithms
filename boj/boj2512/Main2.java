package boj.boj2512;

/*
 * 1. 매개변수 탐색을 이용 : NNNNNNNNNYYYYYYYYY의 꼴로 치환 가능한가
 * 2. 매개변수 탐색을 위해 L,R,M,Result 변수가 필요하다.
 * 3. 최댓값 or 최솟값을 구하시오
 * 4. 문제 뒤집기 어떤 타겟일 때에 어떤 조건을 만족하는가? Y/N
 * 5. 문제 : 총액 이하에서 가능한 최대의 예산을 배정해주기
 * 6. 조건 : 모든 요청이 배정 가능하다면 요청 금액을 그대로 배정한다.
 * 7. 조건2: 모든 요청이 배정 불가하다면 특정 상한액을 설정하여 상한액을 초과하는 요청에는 일괄적으로 상한액으로 배정
 * 8. 상한액 이하의 요청이라면 모두 원하는 대로 해준다.
 * 9. 매개변수꼴: 상한액이 얼마일 때에 최대의 예산을 배정할 수 있는가?
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int N,sum,max,ans,remain_sum;
    static int[] arr;
    static long M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        M = Long.parseLong(st.nextToken());

        sum=0;
        max=0;
        for(int x:arr){
            sum += x;
            max = Math.max(max,x);
        }

        if((long)sum<=M){
            System.out.println(max);
        } else{
            process();
            System.out.println(ans);
        }
    }
    static int setRemain_sum(int mid){
        int check=0;
        //상한액 mid를 받아서 배열에서 상한액보다 큰애를 만나면 그 차를 누적합 하여 리턴
        //그렇게 받아낸 누적합의 값만큼을 기존의 sum에서 빼서 M의 자원으로 수용가능한지를 T/F/로 리턴
        remain_sum =0;
        for(int x:arr){
            if(mid<x){
                remain_sum += (x-mid);
            }
        }
        if((long)(sum-remain_sum)<M){
            return 1; //상한액으로 분배가능하다.
        }else if((long)(sum-remain_sum)==M){
            return 0; //동일하다
        } else{
            return -1;
        }
        //만약 이함수가 t라면 상한액을 더 줄일수 있다.
        //만약 False라면 상한액을 더 키워야 한다.
    }
    static void process(){
        Arrays.sort(arr);
        int start=0;
        int end = max;
        ans = 0;
        while(start <= end){
            int mid = (start+end)/2;
            long budget =0;
            for(int i=0; i<N; i++) {
                if(arr[i]>mid) budget += mid;
                else budget+= arr[i];
            }
            if(budget<=M) {
                start = mid+1;
            }else {
                end = mid-1;
            }
        }
        ans = end;
    }
}
