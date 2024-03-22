package boj.boj1015;
/*
* [BOJ_1015][S4]수열_정렬[MoreEffective][][]
* 기존 방법의 2중 for 문을 없애기 위한 방법,
* 원리는 동일하지만 A배열의 값을 A_cp에서 찾느라 반복문이 중첩되는 대신,
* A를 구조체로 만들어서 자리,값을 둘다 가지도록 한뒤  {idx,value} 값을 기준으로 비내림차순 정렬시킨다면
* 이렇게 정렬된 A_cp 구조체는 값은 정렬되었지만 기존의 인덱스는 유지중인 상태이다.
* 이제 정답 P를 구하려면 정렬된 Elem[] A_cp의 기존인덱스(Elem[i].idx)자리의 요소를 현재 정렬된 인덱스(i) 로 오도록 해주면 된다.
* 그러면 P를 돌면 P의 순서대로 Elem이  있는게 아니니까, A_cp를 순서대로 0 ~ N-1 까지 돌면서 P[Elem[i].idx] 자리에 i를 넣어준다.
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
        public FastReader(String s)throws FileNotFoundException {
            br= new BufferedReader(new FileReader(new File(s)));
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
    static class Elem implements Comparable<Elem>{
        int idx;
        int value;

        @Override
        public int compareTo(Elem other){ //value를 기준으로 정렬할 것, 나-너 가 음수면 그대로 둔다. (오름차순)
            return this.value - other.value;
        }
    }

    static int N;
    static Elem[] A;
    static Elem[] A_cp;
    static int[] P;

    static void input(){
        N = sc.nextInt();
        A = new Elem[N];
        A_cp = new Elem[N];
        P = new int[N];
        int tmp;
        for(int i=0;i<N;i++){
            tmp = sc.nextInt();
            A[i] = new Elem();
            A_cp[i] = new Elem();
            A[i].idx = i;
            A[i].value = tmp;
            A_cp[i].idx = i;
            A_cp[i].value = tmp;
        }
    }
    //P[숫자]에서 숫자는 정렬전 idx라고 보면된다. 그러면 정렬이후 idx로 A_cp를 순차 탐색한다면 A_cp[i].idx 자리를 찾아서 i를 저장해준다.
    //그러면 P의 숫자자리의 숫자는 최종 i위치의 원래 위치를 의미하고, i위치로 가라는 의미로 P[A_cp[i].idx] = i 를 저장한다.
    static void pro(){
        Arrays.sort(A_cp);
        for(int i=0;i<N;i++){
            P[A_cp[i].idx] = i;
        }
        for (int i =0; i<N;i++){
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        input();
        pro();
    }
}
