package acmicpc.이분탐색;

/*
* 두 용액 , 특성값은 -10억~10억이긴한데,,, 아무리 커봐야 -20억 또는 20억 이니까
* 계산 과정에서 int를 넘을 일은 없다.
* 용액의 개수는 2~10만 이긴해도 뭐 10억이 누적될일 없으니 그냥 int 씀
* 특성값을 0을 만들려면,,, 가장음수 + 가장 양수 와 같이 진행해야 한다.
* 그러므로 정렬해서 제일 왼쪽부터 하나씩 잡아서 이분탐색함수에 반전된 값을 주고 찾으라고 시키기
* 찾은 값이랑 더해서 기존의 ans보다 작은지 아닌지를 갱신한다.
* */
import java.io.*;
import java.util.*;
public class boj2470{
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;

    static void input() {
        N = sc.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }
    }

    static int lower_bound(int[] A,int L,int R, int X){
        //X보다 같거나,큰수중에서 가장 왼쪽 인덱스를 리턴한다.
        //X보다 같거나 큰게 유의미 - 오른쪽에서 정답갱신, 왼쪽을 추가 탐색
        int mid;
        int ans = R+1; //타겟보다 같거나 큰값을 줘야 하는데 못찾았으면 R+1 넘겨서 범위에 걸리도록 초기화
        while(L<=R){
            mid = (L+R)/2;
            if(A[mid]>=X){
                R = mid - 1;
                ans = mid;
            }else{
                L = mid +1;
            }
        }
        return ans;
    }

    static void process(){
        Arrays.sort(A,1,N+1);
        int v1=0,v2=0;
        int best_sum = Integer.MAX_VALUE;

        for(int left=1;left<=N-1;left++){
            int candidate = lower_bound(A,left+1,N,-A[left]);

            //후보값은 일단 target보다 같거나 크기 때문에 target 또는 target-1 둘다 체크하도록 한다.
            if(candidate<=N && best_sum > Math.abs(A[candidate]+A[left])){
                best_sum = Math.abs(A[candidate]+A[left]);
                v1 = left;
                v2 = candidate;
            }

            if(left<candidate-1 && best_sum > Math.abs(A[candidate-1]+A[left])){
                best_sum = Math.abs(A[candidate-1]+A[left]);
                v1 = left;
                v2 = candidate-1;
            }

        }
        sb.append(A[v1]).append(' ').append(A[v2]);
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        input();
        process();
    }

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

}













//import java.io.*;
//import java.util.*;
//public class boj2470 {
//    static FastReader sc = new FastReader();
//    static StringBuilder sb = new StringBuilder();
//
//    static int N;
//    static int[] arr;
//    static int min;
//    static int resultL;
//    static int resultR;
//
//    public static void main(String[] args){
//        input();
//        process();
//    }
//
//    static int lower_bound(int L,int R, int target,int[] A){
//        //얘가 해야할일은 L,R을 받아서 target 보다 "같거나" 큰 값중 가장 작은 인덱스를 돌려줘야함
//        //그러면 target보다 큰게 유의미 -> 큰것중 가장 작은것을 가지고 싶음 -> 이미 큰건 버리고
//        //가능성이 있는 같거나 작을수도 있는 범위로 가서 다시 탐색
//        //그러나 정답 갱신은 아쉽더라도 큰쪽에서 한번씩 해줌
//
//        int ans = R+1; //혹시 전부다 타겟보다 작으면 쓸수 있는게 없으니까 L-1 돌려줌
//        while(L<=R){
//            int mid = (L+R)/2;
//            if(A[mid]>=target){ //"같은것도 유의미하다"
//                R = mid -1;
//                ans = mid;
//            }else{
//                L = mid + 1;
//            }
//        }
//        return ans;
//    }
//
//
//    static void input(){
//        N = sc.nextInt();
//        arr = new int[N+1];
//        for(int i=1;i<=N;i++){
//            arr[i] = sc.nextInt();
//        }
//    }
//
//    static void process(){
//        resultR=0;
//        resultL=0;
//        //arr를 정렬하고 min값에 무한대를 넣어둔다.
//        Arrays.sort(arr,1,N+1);
//        min = Integer.MAX_VALUE;
//        //반복문을 0부터 n-2 까지 돌리면서
//        //왼쪽을 잡았으면 현재 상태 그대로 이분탐색에 보내서 lower _bound 인덱스를 가지고 온다.
//
//        for(int i=1;i<=N-1;i++){
//            //여기서 i는 잡은 왼쪽 ( 가상의 가장 작은 값부터 조금씩 증가)
//            int lower = lower_bound(i+1,N,-arr[i],arr);
//
//            if(lower <= N && min > Math.abs(arr[lower]+arr[i])){ //업데이트 될만하면
//                //System.out.printf("i=%d, lower=%d \n",i,lower);
//                min = Math.abs(arr[lower]+arr[i]);
//                resultL = arr[i];
//                resultR = arr[lower];
//            }
//
//            if(i<lower-1 && min > Math.abs(arr[lower-1]+arr[i])){ //업데이트 될만하면
//                //System.out.printf("i=%d, lower=%d \n",i,lower);
//                min = Math.abs(arr[lower-1]+arr[i]);
//                resultL = arr[i];
//                resultR = arr[lower-1];
//            }
//        }
//        System.out.println(resultL+" "+resultR);
//    }
//
//    static class FastReader{
//        BufferedReader br;
//        StringTokenizer st;
//
//        public FastReader(){
//            br = new BufferedReader(new InputStreamReader(System.in));
//        }
//
//        String next(){
//            while(st==null || !st.hasMoreElements()){
//                try{
//                    st = new StringTokenizer(br.readLine());
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//            return st.nextToken();
//        }
//
//        int nextInt(){
//            return Integer.parseInt(next());
//        }
//
//    }
//}
