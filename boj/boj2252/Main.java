package boj.boj2252;
//위상정렬
/*
* 1.정답의 최대치
* - 학생수의 최대치인 10만이 최대이므로 int사용
* 2.복잡도 계산
* - 인접행렬, dfs 사용하면 (3.2만+10만) - 1초 SSap Possible
* 3.문제 이해
* - N명의학생(3.2만), M번의 비교(10만),
* - M번의 비교 횟수를 토대로 방향 그래프의 위상정렬 이용
* - 즉, indeg가 0인 녀석들 부터 출력하면된다.
* 4.풀이 구상
*  - 입력 받으면서 indeg 계산해서 누적
* - indeg가 0인 경우마다 que add 하기
* */
import java.io.*;
import java.util.*;

public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] indeg;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        adj = new ArrayList[N+1];
        indeg = new int[N+1];
        for(int i=1;i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=1;i<=M;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y); //x->y로 갈수 있다. y의 indeg++
            indeg[y]++;
        }
    }

    static void process(){
        Deque<Integer> que = new LinkedList<>();

        //반복문을 한번 돌면서 indeg가 0인 녀석들을 모두 Deque에 추가해준다.
        for(int i=1;i<=N;i++){
            if(indeg[i]==0){
                que.add(i); //학생번호를 추가하기
            }
        }

        //que가 빌때까지 반복하면서 indeg가0 인녀석들을 출력해준다.
        while(!que.isEmpty()){
            int temp = que.poll();
            sb.append(temp).append(' ');

            for(int y:adj[temp]){
                indeg[y]--; //y로 들어가는 간선수 줄이기
                if(indeg[y]==0){
                    que.add(y);
                }
            }
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

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
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
}
