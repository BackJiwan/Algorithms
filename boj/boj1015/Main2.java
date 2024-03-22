package boj.boj1015;
/*
* [BOJ_1015][S4]수열_정렬[14352ms][128KB]
* 1.정답의 최대치(-21억~21억:int)
* -
* 2.복잡도 계산(1초:1억번)
* -
* 3.문제이해
* - 배열 A가 주어진다, 길이 N(N<=50), 한줄에 정수 요소가 나열되어 있다.(0< A[i] <=1000)
* - A[0] = B[P[0]] , A[1] = B[P[1]], A[2] = B[P[2]] 를 만들어내는 P배열을 구하라
* - 결과적으로 비내림차순인 B[i]로 바꾸는 P[i]를 구해야 한다.
* - 2,3,1 -> 1,2,0(적용) -> 1,2,3 (오름차순 + 같아도됨)
* - 즉, 무작위 배열 A[i]를 비내림차순 배열 B[i]로 만들기 위해 A[i]가 B[i]의 어디 위치로 가야하는지를 순서대로 포인팅 하는 배열이
* - P[i]이다.
* 4.구상A
* - 그러면 A[i]=(4,5,3)와 동일한 값을 가지는 A_cp[i]를 Arrays.sort로 정렬한다. 정렬된 A_cp (3,4,5)
* - A[i]는 값과 인덱스를 쌍으로 보고 둘다 가치가 있다. (0,4) , (1,5), (2,3) 이었다.
* - A_cp[i]는 정렬이 되었기 때문에 (0,3) (1,4) (2,5) 여기에서 기존 A와 유의미한 값은 3,4,5이다.
* - N이 50까지로 작기 때문에 N^2을 하더라도 2500 이므로 이거로 반복하면서 연결해도 풀릴것 같은데
* - A[i]를 0~N-1 까지 탐색하면서 A[i]값을 A_cp[j]에서 찾고(반복문) 찾게되면 인덱스인 j를 P[i]에 저장한다.
* 5.추가A
* - 여기 까지 하면 만약 같은수가 여러개 나와서 1이라는 값의 인덱스가 정렬이후 3~5까지라면 모든 1들이 3을 찾는순간 3인덱스만 가지고 오게 된다.
* - A = 6 4 5 4 , A_cp= 4 4 5 6 , P = 3 0 2 0(1)
* - 그러면 숫자를 사용할 때마다 해당 인덱스를 증가시켜서 몇걸음 더 뒤로 가야 하는지를 알리는 used[] 를 만든다. 이것의 인덱스는 숫자이므로 수 범위 크기 만큼
*   used= 0~1000
* - 근데 여기까지 헀어도 사전순출력은...
* */
import java.io.*;
import java.util.*;
public class Main2 {
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
            while(st==null ||!st.hasMoreElements()){
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
    static int[] A,A_cp,P,used;
    static void input(){
        int tmp;
        N = sc.nextInt();
        A = new int[N];
        A_cp = new int[N];
        P = new int[N];
        used = new int[1001];
        for(int i=0;i<N;i++){
            tmp = sc.nextInt();
            A[i] = tmp;
            A_cp[i] = tmp; //정렬을 염두해둔 복제 배열에 동시에 넣기
        }
    }
    static void pro(){
        Arrays.sort(A_cp); //A_cp를 정렬
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(A[i]==A_cp[j]){
                    if(used[A[i]]==0){
                        P[i] = j;
                        used[A[i]]++; //골라낸 A[i] 수를 used 배열의 인덱스에서 찾아서 value를 증가시키고
                        break;
                    }
                    P[i] = j+used[A[i]]; //한칸 뒤로 간다.
                    used[A[i]]++; //골라낸 A[i] 수를 used 배열의 인덱스에서 찾아서 value를 증가시키고
                    break;
                }
            }
        }
        for(int i=0;i<N;i++){
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        input();
        pro();
    }
}
