package acmicpc.트리;

import java.io.*;
import java.util.*;
public class boj11725_2 {
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s)throws FileNotFoundException {
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
    }
    static FastReader sc = new FastReader();
    static StringBuilder sb =new StringBuilder();

    static int N;
    static int[] par;
    static ArrayList<Integer>[] adj;

    static void input(){
        N = sc.nextInt();
        par = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=1;i<N;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static void dfs(int node,int parent){
        for(int k:adj[node]){
            //목표 : par 배열을 채워주기만 하면 된다.
            //adj의 node에 연결된 녀석을 열었는데 본인의 부모라면 갱신안하고 나간다.
            if(k==parent) continue;
            par[k] = node;
            dfs(k,node);
        }
    }

    static void process(){
        dfs(1,-1);

        for(int i=2;i<=N;i++){
            sb.append(par[i]).append('\n');
        }

        System.out.println(sb.toString());


    }

    public static void main(String[] args){
        input();
        process();
    }
}
