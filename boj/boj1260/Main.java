package boj.boj1260;
//인접 리스트를 활용

import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M,V;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt();
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int a = scan.nextInt(),b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        for(int i=1;i<=N;i++){
            Collections.sort(adj[i]);
        }
    }

    static void dfs(int a){
        visit[a] = true;
        sb.append(a).append(' ');

        for(int b:adj[a]){
            if(visit[b])
                continue;
            dfs(b);
        }
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();

        // start는 방문 가능한 점이므로 que에 넣어준다.
        que.add(start);
        visit[start] = true;  // start를 갈 수 있다고 표시하기 (중요!!!)

        while (!que.isEmpty()) {  // 더 확인할 점이 없다면 정지
            int x = que.poll();

            sb.append(x).append(' ');
            for (int y: adj[x]){
                if (visit[y]) continue;  // x 에서 y 를 갈 수는 있지만, 이미 탐색한 점이면 무시

                // y를 갈 수 있으니까 que에 추가하고, visit 처리 하기!
                que.add(y);
                visit[y] = true;
            }
        }
    }

    static void pro(){
        visit = new boolean[N+1];
        dfs(V);
        sb.append('\n');
        for(int i=1;i<=N;i++){
            visit[i] = false;
        }
        bfs(V);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
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
            while(st==null ||!st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e){
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
