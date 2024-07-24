package acmicpc.트리;

import java.io.*;
import java.util.*;
public class boj11725 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

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

    static int N;
    static ArrayList<Integer>[] adj;
    static int[] par;

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
        //node와 parent를 받아서 부모 노드 정보를 갱신해주어야 한다.
        for(int k:adj[node]){
            //adj node와 연결된 것들을 하나씩 꺼내되 부모면 무시한다. -> 자식만 나오게 됨
            if(k==parent) continue;
            par[k] = node;//방금 꺼낸 k의 부모는 node라는 의미이다.
            //이제 자식, 부모 정보를 하나 알고 있으니 dfs를 재귀호출해준다.
            dfs(k,node);
        }
    }

    static void process(){
        //루트를 1이라고 정했기 때문에 dfs(1,-1)로 시작한다.
        dfs(1,-1);

        //dfs를 다 돌고 왔으면 par 배열에 모두 있을 것이다.

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
