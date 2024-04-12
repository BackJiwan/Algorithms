package boj.boj7795;
/*
* 1.정답의 최대치(-21억~21억,int)
* - A의 2만마리 물고기가 모두 최대값, B의 2만마리 물고기가 모두 최소값
* - 모든 A가 모든 B를 먹을 수 있을 때 => 2만^2 => 4억 까지인데 21억 범위에 충분 = int 사용
* 2.복잡도 계산(1초 = 1억번)
* - N,M은 최대 2만
* - B 정렬은 최대 O(log(2만))
* - A에서 하나씩 꺼내는 복잡도 O(2만)
* - O(NlogN) 이지만 N이 2만이니까 충분
* 3.문제이해
* - A와 B 배열이 주어지면
* - A가 B중에서 자기보다 작은 먹이를 먹을 수 있다.
* - 쌍의 개수를 구하여라
* 4.구상A
* - int 배열에 담긴 B를 정렬한다. (logN)
* - A를 앞에서부터 하나씩 뽑는다.(A[i]) 해당 값을
*   B배열에서 이분탐색으로 찾으면서 A[i]보다 작은값중 가장 큰값의 인덱스를 리턴해야 한다.
* */
import java.io.*;
import java.util.*;
public class Main3 {
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
    static int A,B;
    static int[] arrA;
    static int[] arrB;

    static void input(){
        A = sc.nextInt();
        B = sc.nextInt();
        arrA = new int[A+1];
        arrB = new int[B+1];
        for(int i=1;i<=A;i++){
            arrA[i] = sc.nextInt();
        }
        for(int j=1;j<=B;j++){
            arrB[j] = sc.nextInt();
        }
    }
    static int lower_bound(int left,int right,int target,int[] arr){
        //target 보다 작은수중 가장큰 수 인덱스리턴
        //유의미한 범위는 target 보다 작은 범위이다. 작은 범위의
        //못찾았으면 0을 리턴해야 하는데 left는 항상 1이므로 left-1을 하면 0 리턴임
        int result = left-1; //기본값. 즉 못찾았으면 left-1 을 돌려주게 된다. (0을 준다는 의미)
        while(left<=right){ //left가 같은것도 한번은 돌아야 한다.
            //left==right 인 경우를 만났을 경우 mid는 left 가 된다.
            //만약 직전에는 mid가 left -1 이었을 것이고 이것으로 생성한 result는 left 였을 것이다.
            //그러나 left==right 인 경우에 봤는데 이것도 유의미한 경우라면 한칸 더 나가서 답을 찾기 때문에 while 조건은 같은것 도 포함이다.
            int mid = (left+right) / 2;
            if(arr[mid]<target){ //유의미한 범위만 명시하고 나머지는 else 이다.
                //유의미한 이유는 target 보다 작은수중 큰수를 찾는거니까 일단 target 보다 작다면 OK이다.
                //유의미한 범위는 갱신도 가능하다.
                //left~mid 까지 모두 정답 가능하므로 가장큰 mid를 갱신해줌
                //오른쪽을 살릴것, mid가 우리가 찾는 값일수도 있음
                left = mid+1;
                result = mid;
            }else{
                //왼쪽 살릴것, 작은 범위니까 의미 없는 범위이므로 정답 갱신 안함
                right = mid -1;
            }
        }
        return result;
    }
    static void pro(){
        //B배열 정렬
        Arrays.sort(arrB,1,B+1);
        int sum=0;
        for(int i=1;i<arrA.length;i++){ //A에서 하나씩 꺼내서 B 배열의 누구 까지 먹을수 있는지 인덱스 가져옴
            sum += lower_bound(1,B,arrA[i],arrB );
        }
        sb.append(sum).append('\n');
    }
    public static void main(String[] args){
        //System.out.println("2만의 제곱"+Math.pow(20000,2));
        //System.out.println("2만의 밑이 2인 로그 "+Math.log10(20000)/Math.log10(2));
        int tc = sc.nextInt();
        for(int i=0;i<tc;i++){
            input();
            pro();
        }
        System.out.println(sb.toString());
    }
}
