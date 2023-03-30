package boj.boj11725;

import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n;
    static ArrayList<Integer>[] adj;
    static int[] parent;

    public static void main(String[] args){
        input();
        pro();
    }

    static void input(){
        n = scan.nextInt();
        adj = new ArrayList[n+1]; //int[n+1]과 마찬가지이다.
        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=1;i<n;i++){
            int x=scan.nextInt(), y=scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static void pro(){
        dfs(1,-1);
        for(int i=2;i<=n;i++){
            sb.append(parent[i]).append('\n');
        }
        System.out.println(sb);
    }
    static void dfs(int x,int par){
        for(int y:adj[x]){
            //1번 노드가 부모이며 1번에 연결된 자식 노드들을 탐색한다.
            //만약 꺼내본 y가 해당 노드의 부모 노드라면 무시한다.
            if(y==par) continue;
            parent[y] = x; //y의 부모는 x가 맞다. -> x에 연결된 y를 꺼내 본것이기 때문
            dfs(y,x); //y를 기준으로 dfs 재귀호출
        }
    }


    static class FastReader{
        StringTokenizer st;
        BufferedReader br;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws FileNotFoundException{
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
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String s = "";
            try{
                s = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return s;
        }
    }

}
