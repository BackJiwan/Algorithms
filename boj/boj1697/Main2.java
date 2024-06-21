package boj.boj1697;
/*
* 백준 1697번 숨바꼭질
* 1.정답의 최대치 => int 사용
* - 이동 횟수가 가장 많으려면 동생이 1, 수빈이가 10만위치에 있으면 뒤로 10만번 가야 한다.=> 10만 int 충분
* 2.복잡도 계산
* - BFS를 인접 리스트를 이용할 것 이기 때문에
* - O(정점 + 간선) , 정점 = 10만, 간선 = 10만 *3 => O(4* 10만) => 1초 충분
* 3.문제 이해
* - 수빈이의 점 N, 동생의 점 K 는 0~10만 까지 중에 존재한다.
* - 수빈이의 이동은 3개중 하나이다. +1, -1, *2 이렇게 3개
* - 수빈이의 위치에서 동생의 위치로 이동하는 이동횟수 * 1초가 곧 도착시간이다.
* - 가장 빠른 시간을 구해야 한다.
* 4.풀이 구상
* - BFS : 이동거리 계산 가능, 최단거리 계산 가능
* - 수빈이의 위치에서 3개지 간선을 탐색해보면서 BFS를 한뒤에 동생 위치의 dist 배열 값을 확인해보면 알 수 있다.
* -
* */

import java.io.*;
import java.util.*;

public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,K;
    static int[] dist;
    static boolean[] visit;


    static void input(){
        N = sc.nextInt();
        K = sc.nextInt();
        dist = new int[100005];
        visit = new boolean[100005];
    }

    static void process(){
        bfs(N);
        System.out.println(dist[K]);
    }

    static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;
        dist[start] = 0;

        while(!que.isEmpty()){
            int prev = que.poll();
            int next1 = prev-1;
            int next2 = prev+1;
            int next3 = prev*2;
            if(next1>=0 && !visit[next1]){
                visit[next1] = true;
                dist[next1] = dist[prev]+1;
                que.add(next1);
            }
            if(next2<=100000 && !visit[next2]){
                visit[next2] = true;
                dist[next2] = dist[prev]+1;
                que.add(next2);
            }
            if(next3<=100000 && !visit[next3]){
                visit[next3] = true;
                dist[next3] = dist[prev]+1;
                que.add(next3);
            }

        }
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
        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }
        String next(){
            while(st==null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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
