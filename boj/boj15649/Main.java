package boj.boj15649;
/*
* 구상 : 출력은 M개의 수를 가지는 수열이다. -> selected[M+1]
* selected는 재귀적으로 사용된다. -> 앞에서부터 숫자를 고른다 골라지는 숫자 = cand
* 중복X,순서O 이므로 앞에서부터 1~N까지 후보군이 될수 있다.
* 다음 자리수에서는 앞에나온적이 없어야 한다 -> used[cand] = 1로 바꿈 ,used[M+1]은 기본적으로 0으로 초기화 되어 있다.
* rec_func(int k)은 반복문을 통해서 selected[k]에 들어갈 수를 고른다
* case 1 : k가 M인 경우라면 원하는 길이의 수열에 도착했기에 sb.append 를 통해서 결과를 만들고 return
* c1이 아니라면 반복문,cand=1~N까지 증가시키면서 해당회차에 cand가 사용된적있는지 used[cand]==1인경우와 0인경우를 나눈다.
* 만약 cand가 1이라면 방문한적이 있기 때문에 별다른 동작없이 cand++가 실행되도록 놔두면 된다.
* cand가 0인 경우라면 if 조건문으로 잡아서 cand를 사용할수 있기에 rec_func을 실행하기 이전에 used[cand]=1을 하고 rec_func(k+1)호출
* rec_func(k+1)을 나온 경우라면 used[cand]를 다시 0으로 돌려주어야 한다.
* */
import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

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
            while(st==null|| !st.hasMoreElements() ){
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
        long nextLong(){
            return Long.parseLong(next());
        }
        String nextLine(){
            String str = " ";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }

    static int N,M;
    static int selected[];
    static int used[];

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
        used = new int[N+1];
    }
    static void rec_func(int c){
        if(c==M+1){
            for(int i=1;i<M+1;i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int cand=1;cand<N+1;cand++){
            if(used[cand]!=1){ //1이 아니라면=방문한적x
                used[cand] = 1; //방문체크
                selected[c] = cand; //c자리에 cand를 저장
                rec_func(c+1);
                used[cand] = 0;
            }
            //나머지 경우는 방문한적이 있기 때문에 아무 행동없이 반복문의 초기로 돌아간다.  cand를 찾을때까지
        }
    }
    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.println(sb);
    }
}
