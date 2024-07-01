package boj.boj11725;

import java.io.*;
import java.util.*;

public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer> [] adj;
    static int[] parent;

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
    static void input(){
        N = sc.nextInt();
        adj = new ArrayList[N+1];
        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=1;i<N;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static void dfs(int x,int par){
        for(int y:adj[x]){
            if(y==par) continue; //만약 노드의 연결된 정점이 본인의 부모라면 스킵
            parent[y] = x;
            dfs(y,x);
        }
    }

    static void process(){
        dfs(1,-1);

        for(int i=2;i<=N;i++){
            sb.append(parent[i]).append('\n');
        }
        System.out.println(sb);
    }
    public static void main(String[] args){
        input();
        process();
    }
}
