package boj.boj2467;
/*
* 1.정답의 최대치
* - 최악의 경우 10억+10억을 연산하더라도 20억이므로 계산과정 + 정답 까지 int 사용가능
* 2.복잡도 (N=10만)
* - 정렬 : logN
* - 후보선정 : N * 이분탐색 logN
* - 최종 : N*logN = 10만 * log(10만) = 166만, 1초이내 가능
* 3.문제이해
* 4.구상A
* */
import java.io.*;
import java.util.*;
public class Main {
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
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
    }
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] arr;
    static void input(){
        N = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<N+1;i++){
            arr[i] = sc.nextInt();
        }
    }
    static void pro(){
        Arrays.sort(arr,1,N+1);
        int sum=Integer.MAX_VALUE;
        int a1=0,a2=0;
        for(int l=1;l<=N-1;l++){

            int cand = lower_bound(l+1,N,-arr[l],arr); //cand 인덱스로 들어온 후보키

            //제약조건 걸고 타겟 왼쪽수와 결과 갱신
            if(l<(cand-1)&&Math.abs(arr[l]+arr[cand-1])<sum){
                sum = Math.abs(arr[l]+arr[cand-1]);
                a1 = l;
                a2 = cand-1;
            }
            //제약조건 걸고 타겟 오른쪽 수와 결과 갱신
            if(N>=(cand)&&Math.abs(arr[l]+arr[cand])<sum){
                sum = Math.abs(arr[l]+arr[cand]);
                a1 = l;
                a2 = cand;
            }
        }
        sb.append(arr[a1]).append(' ').append(arr[a2]);
        System.out.println(sb.toString());
    }
    static int lower_bound(int L,int R,int target,int[] arr){
        int result = R+1; //답을 내지 못했으면, 의미 없는 수를 던지되, 계산 과정에서 cand-1로 오른쪽 인덱스를 체크하도록 한다.
        //현재 이분탐색에서 설정한 의미 있는 값은 타겟보다 "큰 범위중 가장 작은수" 이다.
        //그렇게 해서 찾은 cand는 앞뒤로 더 적절한 후보가 있을 수 있으므로 타겟보다 큰수와 작은수 이렇게 두개를 비교할 것이다.
        //1. target을 범위에 포함시켜도 되는가? (Y) => 그 이유는 best는 result == 타겟으로 직접 찾아내는 경우이기 때문
        //2. 범위에 포함시키는 범위는 무엇인가?
        while(L<=R){
            int mid = (L+R)/2;
            if(arr[mid]>=target){ //타겟보다 같거나 큰 부분을 골랐으므로 버린다.
                //만약 target같은거 찾으면 이때 mid는 result로 갱신된 뒤에 더이상 result 변화없이 반복문 탈출하니까 신경안써도  ㄱㅊ
                result = mid;
                R = mid-1;
            }else{
                L = mid+1;
            }
        }
        return result;
    }
    public static void main(String[] args){
        //System.out.println(100000*(Math.log10(100000)/Math.log10(2)));
        input();
        pro();
    }
}
