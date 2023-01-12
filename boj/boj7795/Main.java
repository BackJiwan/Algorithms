package boj.boj7795;

/*
* N,M은 int로 커버가능
* A가 B를 먹는다.
* A배열의 숫자 하나를 골라서 이녀석이 B에서 몇번째 인덱스보다 큰지를 판별하면 B에서 그 인덱스 아래로 모두 먹을수 있다는 뜻
* B는 정렬이 필요하다. Array.sort
*
* */

import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int[] A,B;
    static int result;

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N];
        B = new int[M];
        for(int i=0;i<N;i++){
            A[i] = scan.nextInt();
        }
        for(int i=0;i<M;i++){
            B[i] = scan.nextInt();
        }
    }

    static int lower_bound(int target,int L,int R){ //이분탐색을 이용하여 작업결과를 반환
        //반환값은 전달받은 target보다 같거나 큰 인덱스를 반환해준다. (같은것도 못먹기 때문에) 그러면 거기에서 -1 한결과들을 누적
        //배열은 static 변수이므로 따로 전달 x
        int temp = L;
        while(L <= R){
            int mid = ((L+R)/2);
            if(B[mid]<target){
                temp = mid;
                L = mid + 1;
            } else{
                R = mid - 1;
            }
        }
        if(temp==0){
            return L;
        } else{
            return temp+1;
        }
    }

    static void pro(){ //정렬,이분탐색 호출 및 최종 작업
        //먼저 B를 정렬한다
        Arrays.sort(B); //NlogN이므로 괜춚
        result = 0;
        for(int k:A){
            result += lower_bound(k,0,M-1);
        }
        sb.append(result).append("\n");
    }

    public static void main(String[] args){
        int Tc;
        Tc = scan.nextInt();
        for(int t=0;t<Tc;t++){
            input();
            pro();
        }
        System.out.println(sb.toString());
    }



    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st==null || !st.hasMoreElements()){ //st가 아예비어있을떄(초기) 또는 st에 더이상 들어있지 않을때
                try{
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            } catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
}
