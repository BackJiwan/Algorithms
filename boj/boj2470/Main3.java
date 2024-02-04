package boj.boj2470;
/*
* 0.키워드 : '합이 0에 가깝도록' -> A[left]의 -A[left]와 근접한 한개가 대상이 된다. -> `이분탐색`
* 1.정답의 최대치: int 충분
* - 최대 10억,최소 -10억 (int=-20억~20억)
* 2.복잡도0 : N^2
* - 하나를 고르고 남은 모든 숫자와 합을 해보면서 갱신 : N^2 -> 10만의 제곱 => 100억번
* 2.복잡도1: N log N
* - 정렬한번함 : NlogN
* - 모든 수를 작은수부터 Left로 정하고 -Left 와 가장 가까운 Right를 찾는다 (-6 + 6 = 0) :
*   이분탐색: logN, 모든 원소를 left로 정하기 : N -> NlogN
* 3.구현1:
* */
import java.io.*;
import java.util.*;
public class Main3{
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        StringTokenizer st;
        BufferedReader br;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws FileNotFoundException {
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
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
    static int N;
    static int[] A;
    static void input(){
        N = scan.nextInt();
        A = new int[N+1];
        for(int i=1;i<=N;i++){
            A[i] = scan.nextInt();
        }
    }
    static int lowerbound(int[] A,int L,int R,int X){
        //배열 A 중 left 인덱스부터 right 까지 이분탐색하면서 X를 가지는 인덱스 또는 X보다 큰수 중에서 가장 작은 수의 인덱스를 제공한다.
        //먼저 L보다 R이 항상 같거나 클때만 동작할 것이다 = 제한조건
        //그리고 함수의 반복조건이 모두 끝나서 최종장에 도착하더라도 답이 안나온다면 도출할 기본값이 있어야 한다 = 기본값
        //전부 돌아도 candidate가 갱신되지 않았다의 의미는 찾고자 하는 X보다 같거나 큰수가 L~R 사이에 전혀 없다는 의미이므로 기본값 = L-1
        int result = R+1;
        while(L<=R){
            int mid = (L+R)/2;
            //만약 A[mid]가 X와 같은 경우를 왼쪽 버림할지, 오른쪽 버림할지 골라야 하는데 최종적으로 cand와 cand-1을 비교할 예정이기 때문에
            //같은 경우는
            if (A[mid]>=X){ //음수인 A[mid] 대비 같거나 큰수를 가져와야 하기 때문에 같은 범위를 포함시킨다. => 왼쪽보다 오른쪽의 쓸모가 있다는 의미
                //만약 mid 값과 X가 같을때 오른쪽을 버리는 이유는 L+1 로 정답을 비껴갈수도 있기 때문이 다.
//                //R은 R-1로 땡기기 때문에 mid를 남겨둔다.
                result = mid; //만약 arr[mid] == X 인 경우라면 해당줄 덕분에 정답에 갱신이 된 상태로 진행된다.
                R = mid - 1;
            }else{// 타겟 X가 무조건 A[mid] 보다 큰경우 이므로 왼쪽을 전부 버림
                L = mid + 1;
            }
        }
        return result;
    }

    static void pro(){
        //이분 탐색을 위해서 정렬
        Arrays.sort(A,1,N+1);
        int best_sum = Integer.MAX_VALUE;
        int v1=0,v2=0;
        //정렬된 배열의 1~N-1 까지 left를 하나씩 고르고 고른 이후 오른쪽 영역에서
        for(int left=1;left<=N-1;left++){
            //candidate는 목표 범위내에서 찾고자 하는 X타겟값의 인덱스, 만약 없다면 가상의 X위치의 오른쪽 인덱스를 던진다
            //음수인 A와 더해서 가장 작은 합을 만드려면 A또는 A보다 작은수 보다는 A또는 A보다 큰수가 필요하기 때문이다.
            int candidate = lowerbound(A,left+1,N,-A[left]);
            //candidate를 사용한 sum이 기존의 best_sum보다 작고, candidate가 N보다 같거나 작아야 한다
            if(candidate-1>left&&Math.abs(A[left]+A[candidate-1])<best_sum){
                v1 = A[left];
                v2 = A[candidate-1];
                best_sum=Math.abs(A[left]+A[candidate-1]);
            }
            if(candidate<=N&&Math.abs(A[left]+A[candidate])<best_sum){
                v1=A[left];
                v2=A[candidate];
                best_sum = Math.abs(A[left]+A[candidate]);
            }

            //candidate-1를 사용한 sum이 기존의 best_sum보다 작고, candidate-1이 left보다는 같거나 커야 한다.
        }
        //candidate를 탐색하는 lower_bound를 돌려서 얻어낸다.
        //lower_bound는 배열,L,R,목표값을 받아서 목표값또는 목표값이 없다면 가상의 목표값의 바로 오른쪽 인덱스를 리턴한다.
        //만약 전혀 못찾았다면 기본값인 R+1을 리턴한다.
        //candidate는 목표값이 없을경우 목표값보다 바로 다음으로 큰 수를 가져오는데, 이것보다 바로 다음으로 작은 수인
        //candidate-1이 정답에 더 적절할수도 있기 때문에 candidate는 candidate-1과 비교해서 정답을 갱신할지 판단한다.
        sb.append(v1).append(' ').append(v2);
        System.out.println(sb.toString());

    }
    public static void main(String[] args){
        input();
        pro();
    }
}
//import java.io.*;
//import java.util.*;
//public class Main3 {
//    static FastReader scan = new FastReader();
//    static StringBuilder sb = new StringBuilder();
//
//    static class FastReader{
//        StringTokenizer st;
//        BufferedReader br;
//        public FastReader(){
//            br = new BufferedReader(new InputStreamReader(System.in));
//        }
//        public FastReader(String s) throws FileNotFoundException{
//            br = new BufferedReader(new FileReader(new File(s)));
//        }
//
//        String next(){
//            while(st==null||!st.hasMoreElements()) {
//                try {
//                    st = new StringTokenizer(br.readLine());
//                } catch (IOException e) {
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
//        long nextLong(){
//            return Long.parseLong(next());
//        }
//        double nextDouble(){
//            return Double.parseDouble(next());
//        }
//
//        String nextLine(){
//            String str = "";
//            try{
//                str = br.readLine();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//            return str;
//        }
//    }
//    static int N;
//    static int[] arr;
//
//    static void input(){
//        N = scan.nextInt();
//        arr = new int[N+1];
//        for(int i=1;i<=N;i++){
//            arr[i] = scan.nextInt();
//        }
//    }
//
//    static int lower_bound(int[] arr,int L, int R,int X){
//        //A[L~R]에서 X이상의 수 중에서 제일 왼쪽의 idx를 리턴
//        //만약 X이상의 수가 아예 없다면 R+1을 idx로 리턴
//        //즉 X보다 큰 수중 가장 작은 수의 인덱스
//        int result = R+1;
//        while(L<=R){
//            int mid = (L+R)/2;
//            if(arr[mid]>=X){ //찾고자 하는 값이 중간값보다 같거나 적으면 오른쪽을 버림
//                //만약 mid 값과 X가 같을때 오른쪽을 버리는 이유는 L+1 로 정답을 비껴갈수도 있기 때문이 다.
//                //R은 R-1로 땡기기 때문에 mid를 남겨둔다.
//                result = mid; //만약 arr[mid] == X 인 경우라면 해당줄 덕분에 정답에 갱신이 된 상태로 진행된다.
//                R = mid - 1;
//            }else{ //찾고자 하는 값이 중간값보다 무조건 크다, 왼쪽을 mid 포함 버려버림
//                L = mid + 1;
//            }
//        }
//        return result;
//    }
//    static void pro(){
//        //arr를 이분 탐색
//        //정렬
//        Arrays.sort(arr,1,N+1);
//        int best_sum = Integer.MAX_VALUE;
//        int v1=0,v2=0;
//        //전체배열의 1~N-1 까지 왼쪽 요소를 하나씩 증가시켜가며 골라본다 ( O(N) )
//        for(int left=1;left<=N-1;left++){
//            //arr[left]를 골랐으면 -arr[left]와 가장 가까운 수를 찾아야 best_sum이 나온다.
//            //-arr[left]와 가장 가까운 수를 후보로 지정한다.
//            //양수 관점에서 음수쪽을 체크할 이유가 없는 이유는 만약 그러한 후보군이 있다면 이미 음수관점에서 체크할때 걸려들었을 것이기 때문이다.
//            int candidate = lower_bound(arr,left+1,N,-arr[left]);
//            //arr[candidate]가 만약 lower bound 함수에서 찾아지지 않아서 그냥 R+1 을 리턴해버린 경우라면 candidate는 이미 배열의 범위를 넘어버린 것이고
//            //그것을 방지하기 위해서 candidate -1 을 체크해주 어야 한다.
//            //arr[candidate]와 arr[candidate-1] 중에서 어떤 sum이 더 좋은지 체크해서 더 좋은하나를 기존 best_sum과 비교하여 갱신여부를 결정한다.
//            //candidate-1은 잘못하면 left와 동일할수도 있다.
//            if(left<candidate-1 && Math.abs(arr[left]+arr[candidate-1])<best_sum){
//                best_sum = Math.abs(arr[left]+arr[candidate-1]);
//                v1 = arr[left];
//                v2 = arr[candidate - 1];
//            }
//            //candidate는 잘못하면 N을 초과해버릴수도 있다.
//            if(candidate<=N && Math.abs(arr[left]+arr[candidate])<best_sum){
//                best_sum = Math.abs(arr[left]+arr[candidate]);
//                v1 = arr[left];
//                v2 = arr[candidate];
//            }
//
//        }
//        sb.append(v1).append(' ').append(v2);
//        System.out.println(sb);
//
//    }
//    public static void main(String[] args){
//        input();
//        pro();
//    }
//}
