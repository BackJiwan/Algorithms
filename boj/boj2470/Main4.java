package boj.boj2470;
/*
* 1.정답의 최대치(-21억~21억, int)
* - -10억,+10억이 각각 정답이더라도 int 충분하다.
* 2.복잡도 계산(1초=1억번)
* - 쉬운방법 : N개를 골라서 본인을 제외한 나머지 N개와 다 더해보면서 갱신 -> N^2
*   10만^2 = -이 10개, 10억초가 돼서 안된다.
* - 다른방법 : 전체를 탐색하면 수를 고르는 N * 탐색(N) 이지만, 이분탐색하면 logN이 되므로 NlogN이 된다.
*   10만*log2(10만) => 166만으로 1초안에 충분하다.
* 3.문제이해
* - 특성값의 범위는 각 1~10억, -1~ -10억 까지이다.
* - 전체 용액의 수 N은 2~ 10만 까지이다.
* - 존재하는 용액들중 2개를 골라서 합이 0에 가장 가깝게 나와야 한다.
* 4.구상A
* - 키워드 : '두 수의 합 => 0에 가깝게' -> '두 수의 합이 0이다 = A = (-A)'
* - 하나의 수 A를 잡으면, -A와 가까운 수를 찾는다. (탐색)
* - 배열을 먼저 정렬한다.
* - 정렬된 배열의 1번 인덱스부터 하나씩 골라서 A[i] 목표를 -A[i]롤 설정한뒤 lower_bound 이진탐색 함수에 넣어본다.
* - lower_bound는 매개변수로 L,R,[] arr,target 이렇게 4개를 받아서 타겟보다 작은 수중 가장 큰수의 idx를 리턴한다
*   그러면 그렇게 해서 전달된 idx와의 합, 그리고 idx+1 과의 합 이렇게 2개를 비교해서 절대값이 더 0에 가까운 값을 취한다.
*

* */
import java.io.*;
import java.util.*;

public class Main4 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws FileNotFoundException{
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
        String nextLine(){
            String str="";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
    static int N;
    static int[] arr;
    static int a1,a2; //정답으로 사용할 예정
    static int sum=Integer.MAX_VALUE;
    static void input(){
        N = sc.nextInt();
        arr = new int[N+1];
        for(int i=1;i<N+1;i++){
            arr[i] = sc.nextInt();
        }
    }
    static void pro(){
        //먼저 정렬한다. toIndex-1 까지 정렬하므로 종료조건은 실제 idx 보다 항상 1크게 한다.
        Arrays.sort(arr,1,N+1);
        //왼쪽 값을 먼저 고를 예정인데, 마지막 값이 N 이므로, N-1 까지만 왼쪽 값으로 잡으면 끝난다. (복잡도 N)
        for(int i=1;i<=N-1;i++){
            //왼쪽값을 골랐으면 해당 왼쪽 값을 target으로 lower_bound를 호출해야 한다.
            //굳이 내 왼쪽은 볼 필요가 없다.
            //lower_bound 가 던져준 cand는 target인 -arr[i] 보다 같거나작은수중 가장 큰수이다(같을수도 있음)
            //그렇다면 arr[i] + arr[cand] 와 arr[i]+arr[cand+1] 둘 중 절대값이 작은 것을 취해야 하는데
            //만약 cand>=N 이면 +1 연산 자체가 Null을 포인팅 하기 때문에 그냥 arr[i] + arr[cand] 가 답이된다. break
            int cand = lower_bound(i,N,-arr[i],arr);
            if(cand>=N){ //더 이상 오른쪽 탐색 불가능함
                int temp = Math.abs(arr[i]+arr[cand]);
                //만약 temp가 sum 보다 작다면 a1,a2에 기록하고 갱신
                if(temp<sum){
                    a1 = arr[i];
                    a2 = arr[cand];
                    sum = temp;
                }
            }else{
                //cand, cand+1 중 답을 찾아야 함
                if(Math.abs(arr[i]+arr[cand])<Math.abs(arr[i]+arr[cand+1])){
                    //cand의 합이 cand+1 보다 더 작은 경우라면 cand로 갱신할 것이다.
                    int temp = Math.abs(arr[i]+arr[cand]);
                    if(temp<sum){
                        a1 = arr[i];
                        a2 = arr[cand];
                        sum = temp;
                    }
                }else{
                    //cand+1의 합이 cand의 합보다 더 작은 경우이기 때문에 cand+1로 갱신한다.
                    int temp = Math.abs(arr[i]+arr[cand+1]);
                    if(temp<sum){
                        a1 = arr[i];
                        a2 = arr[cand+1];
                        sum = temp;
                    }
                }
            }
        }
        sb.append(a1).append(' ').append(a2);
        System.out.println(sb.toString());
    }
    static int lower_bound(int L,int R,int target,int[] arr){
        //이 함수는 target 보다 1. 작은수 중에서 2.큰수를 찾아야 한다.
        //작은수가 가치있는 범위이자, 작은 범위를찾았다면 일단 갱신해서 마킹하는 것이다.
        if(L==N-1){ //만약 L이 N-1 이면 가능한 후보는 N 하나뿐이다.
            return N;
        }
        int result = L; // 작은수중 큰수를 리턴해야 하므로 아무런 일이 없다면 L이라도 던져야 한다.
        while(L<=R){ //L이 R과 같아진 순간도 계산은 가능하다. 조건만 맞다면
            int mid = (L+R) /2;
            if(arr[mid]<target){ //살릴 범위이기 때문에 같은 것도 포함? -> No
                L = mid+1;
                result = mid;
            }else{
                R = mid -1;
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
