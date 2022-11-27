package boj.boj1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* N개의 정수 , 수열
* 크기가 양수인 부분수열 중 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수 = result
* 첫줄 : N S
* 두번째줄 : N개의 정수가 공백을 두고 주어진다.
* */
public class boj1182 {
    static int N,S;
    static int cnt=0; //경우의 수 세기
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N]; //N크기만큼 배열 생성
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfsFunc(0,0); //출발 위치는0 이고 현재까지 합은 0부터 출발
        if(S==0){
            //만약 총합이 0 이되기 위해서는 dfsFunc이 아무것도 고르지 않는 경우도 카운트 되므로 1을 뺴준다.
            cnt--;
            System.out.println(cnt);
        } else{
            System.out.println(cnt);
        }

    }
    private static void dfsFunc(int index,int sum){
        if(index == N) {//index가 N에 도달했다는 것은 탐색을 모두 마쳤다는 의미이다.
            if(sum == S){ //총합을 저장하는 sum이 S와 동일해진 경우
                cnt++; //경우의 수를 증가
            }
            return;
        }
        dfsFunc(index+1,arr[index]+sum); //다음 요소로 탐색하면서 현재 요소를 더하고 가는 경우
        dfsFunc(index+1,sum); //다음 요소로 탐색하면서 현재 요소를 제외하고 가는경우
    }
}
