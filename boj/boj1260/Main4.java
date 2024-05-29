package boj.boj1260;
/*
* 인접 리스트 이용
* */

import java.io.*;
import java.util.*;
public class Main4 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static int N,M,V;
    static boolean visit[];
    static ArrayList<Integer>[] adj;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int x=sc.nextInt(),y=sc.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        for(int i=1;i<=N;i++){
            Collections.sort(adj[i]);
        }
    }

    static void dfs(int x){
        visit[x]=true;
        sb.append(x).append(' ');
        for(int y:adj[x]){ //x에서 갈수 있는 점들을 하나씩 뽑아서 y에 담는다.
            if(visit[y]) continue;
            dfs(y);
        }
    }

    static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;
        while(!que.isEmpty()){
            int temp = que.poll();
            sb.append(temp).append(' ');
            for(int y:adj[temp]){
                if(visit[y]) continue;
                que.add(y);
                visit[y] = true;
            }
        }
    }

    static void process(){
        visit = new boolean[N+1];
        dfs(V);
        sb.append("\n");
        for(int i=1;i<=N;i++){
            visit[i] = false;
        }
        bfs(V);
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
    }
}
