package acmicpc.정렬.boj1015;

//Comparable<Elem> => public int compareTo(Elem other)
//Elem 입력받을때마다 새로운 Elem을 생성하면서 생성자함수 타서 객체 생성해서 넣을수 있도록 주의하기

// B[P[i]] = A[i]
// 즉 A[0] = B[P[0]]에 적힌 위치로 이동하게 된다.
/*
* 결과적으로 원하는건 수열 P[] 인데
* 가지고 있는 수열 A[]의 매핑테이블로 P를 적용시켜서 비내림차순(오름차순이거나, 같은수도 허용)
* 인 B를 만들어 내는 수열 P를 찾으라는 의미이다.
* 결정적으로 원하는 결과는 비 내림차순인 A를 만든다음에 현재 A를 비내림차순 A로 만드는 방법을 물어보는 것이다.
* B를 정렬시켜버리면 최초의 인덱스가 날라가니까 일을 두번 해야 함 , 그러니까 Elem에 idx랑 value를 둘다 가진뒤
* value를 기준으로 비내림차순 정렬때려버림 그러고 나서 value 순서대로 하나씩 꺼내면서 idx를 정답 배열에 적어줌 ok
* */

import java.io.*;
import java.util.*;
public class boj1015 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class Elem implements Comparable<Elem>{
        int idx;
        int value;

        public Elem(int _idx,int _value){
            this.idx = _idx;
            this.value = _value;
        }

        @Override
        public int compareTo(Elem other){
            return this.value - other.value;
        }
    }

    static int N;
    static int[] P;
    static Elem[] A_temp;

    static void input(){
        N = sc.nextInt();
        P = new int[N];
        A_temp = new Elem[N];
        for(int i =0;i<N;i++){
            int idx = i;
            int value = sc.nextInt();
            A_temp[i] = new Elem(idx,value);
        }
    }

    static void process(){
        //A_temp[] 배열을 value를 기준으로 정렬 때리면 앞에서부터 value만 뽑으면 우리가 원하는 비내림차순 B 배열임
        //근데 각 value에는 기존 A의 인덱스를 idx로 가지고 있음
        //우리가 원하는건 A[i] = B[P[i]]로 만드는 P를 구하고 싶음
        //정렬된 배열이 B니까 A[0]이 B의 어디에 있지?를 찾으려면 정렬된 A_temp.idx = 0 인 녀석의 A_temp.value 가 B의 값인데 이때의 P[i] 값은?
        //아무튼 지금 A_temp[] 배열을 i=0~N-1 까지 깐다고 치면 현재 배열인덱스 i, 안에담긴 최초 A의 idx, 그리고 순서대로 정렬된 value가 있다.
        //정렬된 밸류는 A[] 를 열어본 값이고 A[i]의 i가 알고 싶으면 최초의 idx값이다. 근데 for(int i=0;i<N;i++) 열어보면서
        //A_temp[i]라고 볼때에 i는 P[i]이다. B[P[i]]는 A[i]라고 했으니까 이 값은 value 이다. value를 만들어주는 i는 idx이다.

        Arrays.sort(A_temp);

        for(int i=0;i<N;i++){
            P[A_temp[i].idx] = i;
        }

        for(int t:P){
            sb.append(t).append(' ');
        }
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
    }
}
